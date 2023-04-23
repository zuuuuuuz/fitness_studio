package by.it_academy.fitness.service.converters;

import by.it_academy.fitness.core.dto.Ingredient;
import by.it_academy.fitness.entity.IngredientEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientEntityListToIngredientList implements Converter <List<IngredientEntity>, List<Ingredient>> {

    ProductEntityToProduct productToProductEntity;

    public IngredientEntityListToIngredientList(ProductEntityToProduct productToProductEntity){
        this.productToProductEntity = productToProductEntity;
    }

    @Override
    public List<Ingredient> convert(List<IngredientEntity> ingredientEntityList) {
        List<Ingredient> ingredientList = new ArrayList<>();
        for (IngredientEntity ingredientEntity : ingredientEntityList) {
            ingredientList.add(new Ingredient(productToProductEntity.convert(ingredientEntity.getProduct()),
                    ingredientEntity.getWeight(),
                    BigDecimal.valueOf(ingredientEntity.getProduct().getCalories() * ingredientEntity.getWeight() /
                            ingredientEntity.getProduct().getWeight()).setScale(0, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(ingredientEntity.getProduct().getProteins() * ingredientEntity.getWeight() /
                            ingredientEntity.getProduct().getWeight()).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(ingredientEntity.getProduct().getFats() * ingredientEntity.getWeight() /
                            ingredientEntity.getProduct().getWeight()).setScale(2, RoundingMode.HALF_UP),
                    BigDecimal.valueOf(ingredientEntity.getProduct().getCarbohydrates() * ingredientEntity.getWeight() /
                            ingredientEntity.getProduct().getWeight()).setScale(2, RoundingMode.HALF_UP)));
        }
        return ingredientList;
    }
}
