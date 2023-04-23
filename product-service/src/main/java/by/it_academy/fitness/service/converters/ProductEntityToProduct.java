package by.it_academy.fitness.service.converters;

import by.it_academy.fitness.core.dto.Product;
import by.it_academy.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToProduct implements Converter <ProductEntity, Product> {

    @Override
    public Product convert(ProductEntity productEntity) {
        Product.ProductBuilder productBuilder = Product.ProductBuilder.create();

        Product product = productBuilder.setUuid(productEntity.getUuid())
                .setDtCreate(productEntity.getDtCreate())
                .setDtUpdate(productEntity.getDtUpdate())
                .setTitle(productEntity.getTitle())
                .setWeight(productEntity.getWeight())
                .setCalories(productEntity.getCalories())
                .setProteins(productEntity.getProteins())
                .setFats(productEntity.getFats())
                .setCarbohydrates(productEntity.getCarbohydrates()).build();
        return product;
    }
}
