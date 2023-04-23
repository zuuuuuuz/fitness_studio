package by.it_academy.fitness.service.converters;

import by.it_academy.fitness.core.dto.Product;
import by.it_academy.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductToProductEntity implements Converter <Product, ProductEntity> {

    @Override
    public ProductEntity convert(Product product) {
        return new ProductEntity(UUID.randomUUID(),
                null,
                null,
                product.getTitle(),
                product.getWeight(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates());
    }
}
