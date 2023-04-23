package by.it_academy.fitness.service.api;

import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.Product;
import by.it_academy.fitness.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IProductService {

    void add(Product product);

    Page getPageProducts(int page, int size);

    void updateProduct(UUID uuid, LocalDateTime dt_update, Product product);


    ProductEntity getProductEntity(UUID uuid);

    ProductEntity getProductEntity(String title);
}
