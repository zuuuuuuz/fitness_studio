package by.it_academy.fitness.dao.repositories;

import by.it_academy.fitness.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends CrudRepository <ProductEntity, UUID>, PagingAndSortingRepository <ProductEntity, UUID> {
    Optional<ProductEntity> findByTitle(String title);
}
