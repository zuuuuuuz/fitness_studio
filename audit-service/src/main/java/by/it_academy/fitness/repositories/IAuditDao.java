package by.it_academy.fitness.repositories;

import by.it_academy.fitness.core.entity.AuditEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.UUID;

public interface IAuditDao  extends CrudRepository<AuditEntity, UUID>, PagingAndSortingRepository<AuditEntity, UUID> {

}
