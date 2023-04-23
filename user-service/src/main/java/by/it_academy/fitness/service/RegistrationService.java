package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.*;
import by.it_academy.fitness.core.exception.MultipleErrorResponse;
import by.it_academy.fitness.core.exception.MyError;
import by.it_academy.fitness.core.exception.SingleErrorResponse;
import by.it_academy.fitness.dao.repositories.RegistrationRepository;
import by.it_academy.fitness.entity.RoleEntity;
import by.it_academy.fitness.entity.StatusEntity;
import by.it_academy.fitness.entity.UserCreateEntity;
import by.it_academy.fitness.service.api.IRegistrationService;
import by.it_academy.fitness.service.api.IUserService;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class RegistrationService implements IRegistrationService {

    private static final String URL_MAIL_SERVICE = "http://mail-service:8080/mail/send";
    @Value("${spring.data.redis.url}")
    private String URL;
    private final RegistrationRepository registrationRepository;
    private final IUserService userService;
    private final ConversionService conversionService;
    private final IValidator <UserRegistration> validator;
    private final PasswordEncoder encoder;

    public RegistrationService(RegistrationRepository registrationRepository, IUserService userService, ConversionService conversionService,
                               IValidator <UserRegistration> validator, PasswordEncoder encoder) {
        this.registrationRepository = registrationRepository;
        this.userService = userService;
        this.conversionService = conversionService;
        this.validator = validator;
        this.encoder = encoder;
    }

    @Override
    public void addUser(UserRegistration userRegistration) {
        if(userRegistration == null){
            throw new SingleErrorResponse("Заполните форму для регистрации нового пользователя.");
        }
        if(registrationRepository.findByMail(userRegistration.getMail()).isPresent()){
            throw new SingleErrorResponse("Пользователь с таким email уже существует.");
        }

        validator.validation(userRegistration);

        LocalDateTime dtCreate = LocalDateTime.now();
        UserCreateEntity userCreateEntity = conversionService.convert(userRegistration, UserCreateEntity.class);
        userCreateEntity.setPassword(encoder.encode(userRegistration.getPassword()));
        userCreateEntity.setDtCreate(dtCreate);
        userCreateEntity.setDtUpdate(dtCreate);
        userCreateEntity.setRole(new RoleEntity(UserRole.USER));

        registrationRepository.save(userCreateEntity);

        String message = "Здравствуйте, " + userCreateEntity.getFio() + "! \n" +
                "Добро пожаловать на наш сайт. Пожалуйста, перейдите по ссылки для активации аккаунта " + URL +
                "?code=" + userCreateEntity.getUuid() + "&mail=" + userCreateEntity.getMail();

       sendToMailService(userCreateEntity.getMail(), message);
    }

    @Override
    public void verification(String email, String code) {
        MultipleErrorResponse multipleError = new MultipleErrorResponse();

        if(email == null ||email.isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "mail"));
        }
        if(code == null ||code.isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "code"));
        }

        Optional<UserCreateEntity> optionalUserCreateEntityEmail = registrationRepository.findByMail(email);

        if(optionalUserCreateEntityEmail.isEmpty()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Пользователя с указанным email не найдено.", "mail"));
        }

        UserCreateEntity userCreateEntity = optionalUserCreateEntityEmail.get();
        String userInfo = userCreateEntity.getMail() + userCreateEntity.getUuid();
        String reqInfo = email + code;

        if(!userInfo.equals(reqInfo)){
            throw new SingleErrorResponse("error", "Указаны не верные данные для верификации.");
        }

        if(userCreateEntity.getStatus().getStatus().equals(UserStatus.ACTIVATED)){
            throw new SingleErrorResponse("Ваш аккаунт уже активирован.");
        }
        if(userCreateEntity.getStatus().getStatus().equals(UserStatus.DEACTIVATED)){
            throw new SingleErrorResponse("Ваш аккаунт заблокирован, обратитесь к администратору.");
        }
        if(userCreateEntity.getStatus().getStatus().equals(UserStatus.WAITING_ACTIVATION)){
            userCreateEntity.setDtUpdate(LocalDateTime.now());
            userCreateEntity.setStatus(new StatusEntity(UserStatus.ACTIVATED));
            registrationRepository.save(userCreateEntity);
        }
        if(multipleError.getErrors().size()>0){
            throw multipleError;
        }
    }

    @Override
    public User loging(UserLogin userLogin) {
        MultipleErrorResponse multipleError = new MultipleErrorResponse();

        if(userLogin.getMail() == null || userLogin.getMail().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "mail"));
        }
        if(userLogin.getPassword() == null || userLogin.getPassword().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "password"));
        }

        Optional<UserCreateEntity> optionalUserCreateEntityMail = registrationRepository.findByMail(userLogin.getMail());

        if(optionalUserCreateEntityMail.isEmpty()){
            throw new SingleErrorResponse("Пользователя с email " + userLogin.getMail() + " не существует");
        }
        UserCreateEntity userCreateEntity = optionalUserCreateEntityMail.get();

        if(userCreateEntity.getStatus().getStatus().equals(UserStatus.WAITING_ACTIVATION)){
            throw new SingleErrorResponse("Ваш аккаунт не активирован! Пройдите верификацию!");
        }
        if(userCreateEntity.getStatus().getStatus().equals(UserStatus.DEACTIVATED)){
            throw new SingleErrorResponse("Ваш аккаунт заблокирован, обратитесь к администратору!");
        }
        if(!encoder.matches(userLogin.getPassword(), userCreateEntity.getPassword())){
            throw new SingleErrorResponse("Неверно указан пароль");
        }
        if(multipleError.getErrors().size()>0){
            throw multipleError;
        }
        return conversionService.convert(registrationRepository.findByMail(userLogin.getMail()).get(), User.class);
    }

    @Override
    public User getCard(UUID uuid) {
       return userService.getCard(uuid);
    }

    private void sendToMailService (String mailTo, String massage){

        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(URL_MAIL_SERVICE);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mailTo", mailTo);
        jsonObject.put("message", massage);
        jsonObject.put("subject", "Активация аккаунта");

        httppost.setEntity(new StringEntity(jsonObject.toString(), "UTF-8"));
        httppost.setHeader("Content-Type", "application/json");

        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity entity = response.getEntity();
    }
}
