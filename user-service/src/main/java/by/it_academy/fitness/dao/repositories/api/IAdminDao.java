package by.it_academy.fitness.dao.repositories.api;


import by.it_academy.fitness.entity.UserCreateEntity;
import java.util.UUID;

public interface IAdminDao {

    void save(UserCreateEntity entity);

    UserCreateEntity getCard(UUID uuid);

    void update(UserCreateEntity userCreateEntity);

    UserCreateEntity getUserEntityByEmail(String email);
}
