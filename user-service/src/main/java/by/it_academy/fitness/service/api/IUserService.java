package by.it_academy.fitness.service.api;

import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.User;
import by.it_academy.fitness.core.dto.UserCreate;

import java.time.LocalDateTime;
import java.util.UUID;


public interface IUserService {

    void add (UserCreate userCreate);

    Page getPageUsers(int page, int size);

    void updateUser(UUID uuid, LocalDateTime dt_update, UserCreate userCreate);

    User getCard(UUID uuid);

    User loadUserByUsername(String mail);

}
