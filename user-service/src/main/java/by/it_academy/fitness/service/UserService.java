package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.User;
import by.it_academy.fitness.core.dto.UserCreate;
import by.it_academy.fitness.core.exception.SingleErrorResponse;
import by.it_academy.fitness.dao.repositories.UserRepository;
import by.it_academy.fitness.entity.RoleEntity;
import by.it_academy.fitness.entity.StatusEntity;
import by.it_academy.fitness.entity.UserCreateEntity;
import by.it_academy.fitness.service.api.IUserService;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

public class UserService implements IUserService {
    private static final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private final UserRepository repository;
    private final ConversionService conversionService;
    private final IValidator <UserCreate> validator;
    private final PasswordEncoder encoder;


    public UserService(UserRepository repository, ConversionService conversionService, IValidator <UserCreate> validator,
                       PasswordEncoder encoder) {
        this.repository = repository;
        this.conversionService = conversionService;
        this.validator = validator;
        this.encoder = encoder;
    }

    @Override
    public void add(UserCreate userCreate) {
        if(userCreate == null){
            throw new SingleErrorResponse("Заполните форму для регистрации нового пользователя.");
        }
        if(repository.findByMail(userCreate.getMail()).isPresent()){
            throw new SingleErrorResponse("Пользователь с таким email уже существует.");
        }

        validator.validation(userCreate);

        UserCreateEntity userCreateEntity = conversionService.convert(userCreate, UserCreateEntity.class);
        userCreateEntity.setPassword(encoder.encode(userCreate.getPassword()));
        LocalDateTime dtCreate = LocalDateTime.now();
        userCreateEntity.setDtCreate(dtCreate);

        repository.save(userCreateEntity);
    }

    @Override
    public User getCard(UUID uuid) {
        Optional<UserCreateEntity> optionalUserCreateEntity = repository.findById(uuid);

        if (optionalUserCreateEntity.isEmpty()) {
            throw new SingleErrorResponse("Пользователя с указанным uuid не найдено.");
        }
        UserCreateEntity userCreateEntity = optionalUserCreateEntity.get();
        User user = conversionService.convert(userCreateEntity, User.class);
        return user;
    }

    @Override
    public Page <User> getPageUsers(int page, int size) {
        org.springframework.data.domain.Page<UserCreateEntity> userCreateEntityPage = repository.findAll(PageRequest.of(page, size));

        List<UserCreateEntity> userCreateEntity = userCreateEntityPage.toList();
        List <User> users = new ArrayList<>();

        for (UserCreateEntity createEntity : userCreateEntity) {
            users.add(conversionService.convert(createEntity, User.class));
        }

        Page<User> pageOfUser = new Page<>(userCreateEntityPage.getNumber(),
                userCreateEntityPage.getSize(),
                userCreateEntityPage.getTotalPages(),
                userCreateEntityPage.getTotalElements(),
                userCreateEntityPage.isFirst(),
                userCreateEntityPage.getNumberOfElements(),
                userCreateEntityPage.isLast(),
                users);
        return pageOfUser;
    }

    @Override
    public void updateUser(UUID uuid, LocalDateTime dtUpdate, UserCreate userCreate){
        if(uuid == null || userCreate == null){
            throw new SingleErrorResponse("Не переданы параметры для обновления");
        }
        validator.validation(userCreate);

        Optional<UserCreateEntity> optionalUserCreateEntityUUID = repository.findById(uuid);

        if(optionalUserCreateEntityUUID.isEmpty()){
            throw new SingleErrorResponse("Такого пользователя для обновления не существует");
        }

        UserCreateEntity userCreateEntity = optionalUserCreateEntityUUID.get();

        if(!userCreateEntity.getDtUpdate().equals(dtUpdate)){
            throw new SingleErrorResponse("У вас не актуальная версия");
        }

        Optional<UserCreateEntity> optionalUserCreateEntityMail = repository.findByMail(userCreate.getMail());

        if(optionalUserCreateEntityMail.isPresent()
                && !optionalUserCreateEntityMail.get().getUuid().equals(uuid)){
            throw new SingleErrorResponse("Указанный email уже используется другим пользователем.");
        }

        userCreateEntity.setMail(userCreate.getMail());
        userCreateEntity.setFio(userCreate.getFio());
        userCreateEntity.setRole(new RoleEntity(userCreate.getRole()));
        userCreateEntity.setDtUpdate(LocalDateTime.now());
        userCreateEntity.setStatus(new StatusEntity(userCreate.getStatus()));
        userCreateEntity.setPassword(encoder.encode(userCreate.getPassword()));
        repository.save(userCreateEntity);
    }

    @Override
    public User loadUserByUsername (String mail){
        return conversionService.convert(repository.findByMail(mail).get(), User.class);
    }
}
