
package by.it_academy.fitness.service.validators;

import by.it_academy.fitness.core.dto.Product;
import by.it_academy.fitness.core.exception.MultipleErrorResponse;
import by.it_academy.fitness.core.exception.MyError;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator implements IValidator <Product> {


    @Override
    public void validation(Product product) {
        MultipleErrorResponse multipleError = new MultipleErrorResponse();

        if(product.getTitle() == null || product.getTitle().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "title"));
        }
        if(product.getTitle().length() > 255){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Значение поля превышает 255 символов.", "title"));
        }
        if(product.getWeight() == 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не может быть равно 0.", "weight"));
        }
        if(product.getWeight() < 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не может быть отрицательным.", "weight"));
        }
        if(product.getCalories() == 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не может быть равно 0.", "calories"));
        }
        if(product.getCalories() < 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не может быть отрицательным.", "calories"));
        }
        if(product.getProteins() == 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не может быть равно 0.", "proteins"));
        }
        if(product.getProteins() < 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не может быть отрицательным.", "proteins"));
        }
        if(product.getFats() < 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не может быть отрицательным.", "fats"));
        }
        if(product.getCarbohydrates() == 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не может быть равно 0.", "carbohydrates"));
        }
        if(product.getCarbohydrates() < 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не может быть отрицательным.", "carbohydrates"));
        }
        if(multipleError.getErrors().size()>0){
            throw multipleError;
        }
    }
}
