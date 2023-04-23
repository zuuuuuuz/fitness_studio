package by.it_academy.fitness.dao.repositories.api;

import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.Product;
import java.util.UUID;

public interface IProductDao {
    void add(Product product);

    Page getPageProducts(int page, int size);

    void updateProduct(UUID uuid, long dt_update, Product product);
}
