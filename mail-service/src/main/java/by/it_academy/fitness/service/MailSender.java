package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.Mail;
import by.it_academy.fitness.service.api.IMailService;
import by.it_academy.fitness.service.validators.api.IValidator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender implements IMailService {
    private IValidator validator;

    public MailSender(IValidator validator) {
        this.validator = validator;
    }

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;
    @Override
    public void send(Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        validator.validation(mail);
        mailMessage.setFrom(username);
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailSender.send(mailMessage);
    }
}
