package by.it_academy.fitness.web.controllers;

import by.it_academy.fitness.core.dto.Mail;
import by.it_academy.fitness.service.api.IMailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail/send")
public class MailController {
    private final IMailService mailService;

    public MailController(IMailService mailService) {
            this.mailService = mailService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Mail mail){
        mailService.send(mail);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
