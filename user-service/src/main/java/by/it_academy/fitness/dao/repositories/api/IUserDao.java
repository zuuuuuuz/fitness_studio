package by.it_academy.fitness.dao.repositories.api;

import by.it_academy.fitness.core.dto.UserLogin;
import by.it_academy.fitness.entity.UserCreateEntity;
import java.util.UUID;

public interface IUserDao {
    void save(UserCreateEntity entity);

    void verification(UserCreateEntity userCreateEntity);

    void loging(UserLogin userLogin);

    UserCreateEntity getCard(UUID uuid);

    UserCreateEntity getUserEntityByEmail(String email);
}
