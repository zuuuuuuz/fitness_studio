package by.it_academy.fitness.service.converters;

import by.it_academy.fitness.core.dto.UserRegistration;
import by.it_academy.fitness.core.dto.UserRole;
import by.it_academy.fitness.core.dto.UserStatus;
import by.it_academy.fitness.entity.RoleEntity;
import by.it_academy.fitness.entity.StatusEntity;
import by.it_academy.fitness.entity.UserCreateEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class UserRegistrationToUserEntity implements Converter <UserRegistration, UserCreateEntity> {

    @Override
    public UserCreateEntity convert(UserRegistration userRegistration) {
        return new UserCreateEntity(UUID.randomUUID(),
                userRegistration.getFio(),
                null,
                null,
                userRegistration.getMail(),
                new RoleEntity(UserRole.USER),
                new StatusEntity(UserStatus.WAITING_ACTIVATION),
                userRegistration.getPassword());
    }
}
