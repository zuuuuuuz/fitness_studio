package by.it_academy.fitness.dao.repositories;

import by.it_academy.fitness.entity.IngredientEntity;
import by.it_academy.fitness.entity.RecipeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends CrudRepository <RecipeEntity, UUID>, PagingAndSortingRepository <RecipeEntity, UUID> {
    Optional<RecipeEntity> findByTitle(String title);
}
