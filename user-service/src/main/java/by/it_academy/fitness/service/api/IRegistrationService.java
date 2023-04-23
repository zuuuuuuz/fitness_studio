package by.it_academy.fitness.service.api;

import by.it_academy.fitness.core.dto.User;
import by.it_academy.fitness.core.dto.UserLogin;
import by.it_academy.fitness.core.dto.UserRegistration;
import java.util.UUID;

public interface IRegistrationService {

    void addUser(UserRegistration userRegistration);

    void verification(String email, String code);

    User loging(UserLogin userLogin);

    User getCard(UUID uuid);

}
