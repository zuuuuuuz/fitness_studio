package by.it_academy.fitness.config;

import by.it_academy.fitness.core.dto.Product;
import by.it_academy.fitness.core.dto.RecipeForCU;
import by.it_academy.fitness.dao.repositories.ProductRepository;
import by.it_academy.fitness.dao.repositories.RecipeRepository;
import by.it_academy.fitness.service.*;
import by.it_academy.fitness.service.api.*;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class ServiceConfig {

    @Bean
    public IProductService productService(ProductRepository repository, ConversionService conversionService, IValidator <Product> validator){
        return new ProductService(repository, conversionService, validator);
    }

    @Bean
    public IRecipeService recipeService(RecipeRepository repository, ProductService productService,
                                        ConversionService conversionService, IValidator<RecipeForCU> validator){
        return new RecipeService(repository, productService, conversionService, validator);
    }
}
