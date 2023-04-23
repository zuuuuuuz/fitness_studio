package by.it_academy.fitness.service.validators;

import by.it_academy.fitness.core.dto.UserCreate;
import by.it_academy.fitness.core.dto.UserRole;
import by.it_academy.fitness.core.dto.UserStatus;
import by.it_academy.fitness.core.exception.MultipleErrorResponse;
import by.it_academy.fitness.core.exception.MyError;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.regex.Pattern;
@Component
public class UserCreateValidator implements IValidator <UserCreate> {

    private static final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void validation(UserCreate userCreate) {
        MultipleErrorResponse multipleError = new MultipleErrorResponse();

        if(userCreate.getMail() == null || userCreate.getMail().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "mail"));
        }
        if(userCreate.getMail().length() > 255){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Значение поля превышает 255 символов.", "mail"));
        }
        if(userCreate.getPassword() == null || userCreate.getPassword().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "password"));
        }
        if(userCreate.getPassword().length() > 255){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Значение поля превышает 255 символов.", "password"));
        }
        if(userCreate.getFio() == null || userCreate.getFio().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "fio"));
        }
        if(userCreate.getFio().length() > 255){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Значение поля превышает 255 символов.", "fio"));
        }
        if(userCreate.getRole() == null){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "role"));
        }
        if(!Arrays.stream(UserRole.values()).anyMatch(element -> element == userCreate.getRole())){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Указанной роли не существует.", "role"));
        }
        if(userCreate.getStatus() == null){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "role"));
        }
        if(!Arrays.stream(UserStatus.values()).anyMatch(element -> element == userCreate.getStatus())){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Указанной роли не существует.", "role"));
        }

        if(!EMAIL_PATTERN.matcher(userCreate.getMail()).matches()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Проверьте корректность введенной почты.", "mail"));
        }

        if(multipleError.getErrors().size()>0){
            throw multipleError;
        }
    }
}
