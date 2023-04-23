package by.it_academy.fitness.dao.repositories;

import by.it_academy.fitness.entity.UserCreateEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface RegistrationRepository extends CrudRepository<UserCreateEntity, UUID> {
        Optional<UserCreateEntity> findById(UUID uuid);

        Optional<UserCreateEntity> findByMail(String mail);

}

