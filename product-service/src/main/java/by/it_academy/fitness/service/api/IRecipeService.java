package by.it_academy.fitness.service.api;

import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.Recipe;
import by.it_academy.fitness.core.dto.RecipeForCU;
import by.it_academy.fitness.entity.RecipeEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IRecipeService {

    void add(RecipeForCU recipeForCU);

    Page getPageRecipes(int page, int size);

    void updateRecipe(UUID uuid, LocalDateTime dt_update, RecipeForCU recipeForCU);


    RecipeEntity getRecipeEntity(UUID uuid);

    RecipeEntity getRecipeEntity(String title);
}
