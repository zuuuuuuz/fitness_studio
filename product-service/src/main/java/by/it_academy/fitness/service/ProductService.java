package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.Product;
import by.it_academy.fitness.core.exception.SingleErrorResponse;
import by.it_academy.fitness.dao.repositories.ProductRepository;
import by.it_academy.fitness.entity.ProductEntity;
import by.it_academy.fitness.service.api.IProductService;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductService implements IProductService {
    private final ProductRepository repository;
    private final ConversionService conversionService;
    private final IValidator <Product> validator;

    public ProductService(ProductRepository repository, ConversionService conversionService, IValidator <Product> validator) {
        this.repository = repository;
        this.conversionService = conversionService;
        this.validator = validator;
    }
    @Override
    public void add(Product product) {
        if(product == null){
            throw new SingleErrorResponse("Заполните форму для регистрации нового пользователя.");
        }
        if(repository.findByTitle(product.getTitle()).isPresent()){
            throw new SingleErrorResponse("Продукт с таким названием уже существует.");
        }

        validator.validation(product);

        LocalDateTime dtCreate = LocalDateTime.now();
        ProductEntity productEntity = conversionService.convert(product, ProductEntity.class);
        productEntity.setDtCreate(dtCreate);
        productEntity.setDtUpdate(dtCreate);
        repository.save(productEntity);
    }

    @Override
    public Page<Product> getPageProducts(int page, int size) {
        org.springframework.data.domain.Page<ProductEntity> productEntityPage = repository.findAll(PageRequest.of(page, size));

        List<ProductEntity> productEntities = productEntityPage.toList();
        List<Product> products = new ArrayList<>();

        for (ProductEntity productEntity : productEntities) {
            products.add(conversionService.convert(productEntity, Product.class));
        }

        Page<Product> pageOfProduct = new Page<>(productEntityPage.getNumber(),
                productEntityPage.getSize(),
                productEntityPage.getTotalPages(),
                productEntityPage.getTotalElements(),
                productEntityPage.isFirst(),
                productEntityPage.getNumberOfElements(),
                productEntityPage.isLast(),
                products);
        return pageOfProduct;
    }

    @Override
    public void updateProduct(UUID uuid, LocalDateTime dtUpdate, Product product) {
        if(uuid == null || product == null){
            throw new SingleErrorResponse("Не переданы параметры для обновления");
        }
        validator.validation(product);

        Optional <ProductEntity> optionalProductEntityUUID = repository.findById(uuid);
        Optional <ProductEntity> optionalProductEntityTitle = repository.findByTitle(product.getTitle());

        if(optionalProductEntityUUID.isEmpty()){
            throw new SingleErrorResponse("Такого продукта для обновления не существует");
        }

        ProductEntity productEntity = optionalProductEntityUUID.get();

        if(!productEntity.getDtUpdate().equals(dtUpdate)){
            throw new SingleErrorResponse("У вас не актуальная версия");
        }

        if(optionalProductEntityTitle.isPresent() && !optionalProductEntityTitle.get().getUuid().equals(uuid)){
            throw new SingleErrorResponse("Указанное название продукта уже используется.");
        }
        productEntity.setTitle(product.getTitle());
        productEntity.setWeight(product.getWeight());
        productEntity.setCalories(product.getCalories());
        productEntity.setProteins(product.getProteins());
        productEntity.setFats(product.getFats());
        productEntity.setCarbohydrates(product.getCarbohydrates());
        repository.save(productEntity);
    }

    @Override
    public ProductEntity getProductEntity(UUID uuid){
        Optional <ProductEntity> optionalProductEntityUUID = repository.findById(uuid);
        if(optionalProductEntityUUID.isEmpty()){
            throw new SingleErrorResponse("Продукта с указанным uuid не найдено.");
        }
        return optionalProductEntityUUID.get();
    }
    @Override
    public ProductEntity getProductEntity(String title){
        Optional <ProductEntity> optionalProductEntityTitle = repository.findByTitle(title);
        if(optionalProductEntityTitle.isEmpty()){
            throw new SingleErrorResponse("Продукта с указанным названием не найдено.");
        }
        return optionalProductEntityTitle.get();
    }
}
