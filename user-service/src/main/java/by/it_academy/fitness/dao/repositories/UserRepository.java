package by.it_academy.fitness.dao.repositories;

import by.it_academy.fitness.entity.UserCreateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository <UserCreateEntity, UUID>, PagingAndSortingRepository <UserCreateEntity, UUID> {

        Optional<UserCreateEntity> findByMail(String mail);

}

