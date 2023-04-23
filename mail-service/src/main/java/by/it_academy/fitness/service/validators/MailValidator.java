package by.it_academy.fitness.service.validators;

import by.it_academy.fitness.core.dto.Mail;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;
@Component
public class MailValidator implements IValidator<Mail> {

    private static final String EMAIL_REGEX =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void validation(Mail mail) {

        if(!EMAIL_PATTERN.matcher(mail.getMailTo()).matches()){
           throw new IllegalArgumentException("Проверьте корректность введенной почты");
        }
    }
}
