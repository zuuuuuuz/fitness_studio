package by.it_academy.fitness.config;

import by.it_academy.fitness.core.dto.UserCreate;
import by.it_academy.fitness.core.dto.UserRegistration;
import by.it_academy.fitness.dao.repositories.UserRepository;
import by.it_academy.fitness.dao.repositories.RegistrationRepository;
import by.it_academy.fitness.service.*;
import by.it_academy.fitness.service.api.*;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
public class ServiceConfig {

    @Bean
    public IUserService userServiceService(UserRepository repository, ConversionService conversionService,
                                           IValidator <UserCreate> validator, PasswordEncoder encoder){
        return new UserService(repository, conversionService, validator, encoder);
    }

    @Bean
    public IRegistrationService registrationService(RegistrationRepository registrationRepository,
                                                    IUserService userService,
                                                    ConversionService conversionService,
                                                    IValidator <UserRegistration> validator,
                                                    PasswordEncoder encoder){
        return new RegistrationService(registrationRepository, userService, conversionService, validator, encoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
