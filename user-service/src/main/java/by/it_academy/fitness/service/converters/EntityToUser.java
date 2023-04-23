package by.it_academy.fitness.service.converters;

import by.it_academy.fitness.core.dto.User;
import by.it_academy.fitness.entity.UserCreateEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToUser implements Converter <UserCreateEntity, User> {

    @Override
    public User convert(UserCreateEntity userEntity) {
        return new User(userEntity.getUuid(),
                userEntity.getDtCreate(),
                userEntity.getDtUpdate(),
                userEntity.getMail(),
                userEntity.getFio(),
                userEntity.getRole().getRole(),
                userEntity.getStatus().getStatus());
    }
}
