package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.*;
import by.it_academy.fitness.core.exception.SingleErrorResponse;
import by.it_academy.fitness.dao.repositories.RecipeRepository;
import by.it_academy.fitness.entity.IngredientEntity;
import by.it_academy.fitness.entity.ProductEntity;
import by.it_academy.fitness.entity.RecipeEntity;
import by.it_academy.fitness.service.api.IRecipeService;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class RecipeService implements IRecipeService {

    private final RecipeRepository repository;
    private final ProductService productService;
    private final ConversionService conversionService;
    private final IValidator <RecipeForCU> validator;

    public RecipeService(RecipeRepository repository, ProductService productService, ConversionService conversionService,
                         IValidator <RecipeForCU> validator) {
        this.repository = repository;
        this.productService = productService;
        this. conversionService = conversionService;
        this.validator = validator;
    }

    @Override
    public void add(RecipeForCU recipeForCU) {
        if(recipeForCU == null){
            throw new SingleErrorResponse("Заполните форму для регистрации нового рецепта.");
        }

        validator.validation(recipeForCU);
        long timeLong = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();

        LocalDateTime dtCreate = LocalDateTime.ofInstant(Instant.ofEpochMilli(timeLong), ZoneOffset.UTC);
        LocalDateTime dtUpdate = dtCreate;

        List<IngredientEntity> ingredientEntityList = new ArrayList<>();
        List<Ingredient> ingredientList = recipeForCU.getComposition();

        ProductEntity productEntity;

        for (Ingredient ingredient : ingredientList) {
            UUID productUUID = ingredient.getProduct().getUuid();
            productEntity = productService.getProductEntity(productUUID);
            ingredientEntityList.add(new IngredientEntity(productEntity, ingredient.getWeight()));
        }

        RecipeEntity recipeEntity = new RecipeEntity(UUID.randomUUID(),
                dtCreate,
                dtUpdate,
                recipeForCU.getTitle(),
                ingredientEntityList);

        Optional<RecipeEntity> optionalRecipeEntity = repository.findByTitle(recipeForCU.getTitle());
        if(optionalRecipeEntity.isPresent()){
            if(recipeEntity.getTitle().equals(optionalRecipeEntity.get().getTitle()) &&
                    recipeEntity.getComposition().equals(optionalRecipeEntity.get().getComposition())){
                throw new SingleErrorResponse("Такой рецепт уже существует.");
            }
        }
        repository.save(recipeEntity);
    }

    @Override
    public Page <Recipe> getPageRecipes(int page, int size) {
        Recipe.RecipeBuilder recipeBuilder = Recipe.RecipeBuilder.create();
        org.springframework.data.domain.Page<RecipeEntity> recipeEntityPage = repository.findAll(PageRequest.of(page, size));

        List <Recipe> recipes = new ArrayList<>();
        List<RecipeEntity> recipeEntities = recipeEntityPage.toList();
        List<Ingredient> ingredientList = new ArrayList<>();


        for (RecipeEntity recipeEntity : recipeEntities) {
            ingredientList = conversionService.convert(recipeEntity.getComposition(), List.class);

            ProductEntity productEntity;

            int totalWeight = 0;
            int totalCalories = 0;
            double totalProteins = 0.0;
            double totalFats = 0.0;
            double totalCarbohydrates = 0.0;

            for (Ingredient ingredient : ingredientList) {
                UUID productUUID = ingredient.getProduct().getUuid();
                productEntity = productService.getProductEntity(productUUID);
                double part = 1d * ingredient.getWeight() / productEntity.getWeight();

                totalWeight += ingredient.getWeight();
                totalCalories += productEntity.getCalories() * part;
                totalProteins += productEntity.getProteins() * part;
                totalFats += productEntity.getFats() * part;
                totalCarbohydrates += productEntity.getCarbohydrates() * part;
            }

            recipes.add(recipeBuilder.setUuid(recipeEntity.getUuid())
                    .setDtCreate(recipeEntity.getDtCreate())
                    .setDtUpdate(recipeEntity.getDtUpdate())
                    .setTitle(recipeEntity.getTitle())
                    .setComposition(ingredientList)
                    .setWeight(totalWeight)
                    .setCalories(totalCalories)
                    .setProteins(BigDecimal.valueOf(totalProteins).setScale(2, RoundingMode.UP))
                    .setFats(BigDecimal.valueOf(totalFats).setScale(2, RoundingMode.UP))
                    .setCarbohydrates(BigDecimal.valueOf(totalCarbohydrates).setScale(2, RoundingMode.UP)).build());
        }

        Page<Recipe> pageOfRecipe = new Page<>(recipeEntityPage.getNumber(),
                recipeEntityPage.getSize(),
                recipeEntityPage.getTotalPages(),
                recipeEntityPage.getTotalElements(),
                recipeEntityPage.isFirst(),
                recipeEntityPage.getNumberOfElements(),
                recipeEntityPage.isLast(),
                recipes);
        return pageOfRecipe;
    }

    @Override
    public void updateRecipe(UUID uuid, LocalDateTime dtUpdate, RecipeForCU recipeForCU) {
        if(uuid == null || recipeForCU == null){
            throw new SingleErrorResponse("Не переданы параметры для обновления");
        }
        validator.validation(recipeForCU);

        Optional<RecipeEntity> optionalRecipeEntityUUID = repository.findById(uuid);
        Optional<RecipeEntity> optionalRecipeEntityTitle = repository.findByTitle(recipeForCU.getTitle());

        if(optionalRecipeEntityUUID.isEmpty()){
            throw new SingleErrorResponse("Такого рецепта для обновления не существует");
        }

        RecipeEntity recipeEntity = optionalRecipeEntityUUID.get();

        if(!recipeEntity.getDtUpdate().equals(dtUpdate)){
            throw new SingleErrorResponse("У вас не актуальная версия");
        }
        if(optionalRecipeEntityTitle.isPresent() && !optionalRecipeEntityTitle.get().getUuid().equals(uuid)){
            throw new SingleErrorResponse("Указанное название рецепта уже используется.");
        }
        List<IngredientEntity> ingredientEntityList = new ArrayList<>();
        List<Ingredient> ingredientList = recipeForCU.getComposition();

        ProductEntity productEntity;

        for (Ingredient ingredient : ingredientList) {
            UUID productUUID = ingredient.getProduct().getUuid();
            productEntity = productService.getProductEntity(productUUID);
            ingredientEntityList.add(new IngredientEntity(productEntity, ingredient.getWeight()));
        }

        recipeEntity.setTitle(recipeForCU.getTitle());
        recipeEntity.setComposition(ingredientEntityList);

        repository.save(recipeEntity);
    }
    @Override
    public RecipeEntity getRecipeEntity(UUID uuid){
        Optional <RecipeEntity> optionalRecipeEntityUUID = repository.findById(uuid);
        if(optionalRecipeEntityUUID.isEmpty()){
            throw new SingleErrorResponse("Рецепт с указанным uuid не найден.");
        }
        return optionalRecipeEntityUUID.get();
    }
    @Override
    public RecipeEntity getRecipeEntity(String title){
        Optional <RecipeEntity> optionalRecipeEntityTitle = repository.findByTitle(title);
        if(optionalRecipeEntityTitle.isEmpty()){
            throw new SingleErrorResponse("Рецепт с указанным названием не найден.");
        }
        return optionalRecipeEntityTitle.get();
    }

}
