package by.it_academy.fitness.service.validators;

import by.it_academy.fitness.core.dto.UserRegistration;
import by.it_academy.fitness.core.exception.MultipleErrorResponse;
import by.it_academy.fitness.core.exception.MyError;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class UserRegistrationValidator implements IValidator <UserRegistration> {

    private static final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void validation(UserRegistration userRegistration) {
        MultipleErrorResponse multipleError = new MultipleErrorResponse();

        if(userRegistration.getMail() == null || userRegistration.getMail().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "mail"));
        }
        if(userRegistration.getMail().length() > 255){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Значение поля превышает 255 символов.", "mail"));
        }
        if(userRegistration.getPassword() == null || userRegistration.getPassword().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "password"));
        }
        if(userRegistration.getPassword().length() > 255){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Значение поля превышает 255 символов.", "password"));
        }
        if(userRegistration.getFio() == null || userRegistration.getFio().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "fio"));
        }
        if(userRegistration.getFio().length() > 255){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Значение поля превышает 255 символов.", "fio"));
        }
        if(!EMAIL_PATTERN.matcher(userRegistration.getMail()).matches()){
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
