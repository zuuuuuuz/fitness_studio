package by.it_academy.fitness.dao.repositories.api;

import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.Recipe;

import java.util.UUID;

public interface IRecipeDao {
    void add(Recipe recipe);

    Page getPageProducts(int page, int size);

    void updateProduct(UUID uuid, long dt_update, Recipe recipe);
}
