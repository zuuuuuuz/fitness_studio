package by.it_academy.fitness.service.converters;

import by.it_academy.fitness.core.dto.UserCreate;
import by.it_academy.fitness.core.dto.UserRole;
import by.it_academy.fitness.entity.RoleEntity;
import by.it_academy.fitness.entity.StatusEntity;
import by.it_academy.fitness.entity.UserCreateEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class UserToUserEntity implements Converter <UserCreate, UserCreateEntity> {

    @Override
    public UserCreateEntity convert(UserCreate userCreate) {
        return new UserCreateEntity(UUID.randomUUID(),
                userCreate.getFio(),
                null,
                null,
                userCreate.getMail(),
                new RoleEntity(userCreate.getRole()),
                new StatusEntity(userCreate.getStatus()),
                userCreate.getPassword());
    }
}
