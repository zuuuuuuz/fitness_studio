package by.it_academy.fitness.service.validators;

import by.it_academy.fitness.core.dto.Ingredient;
import by.it_academy.fitness.core.dto.RecipeForCU;
import by.it_academy.fitness.core.exception.MultipleErrorResponse;
import by.it_academy.fitness.core.exception.MyError;
import by.it_academy.fitness.service.validators.api.IValidator;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RecipeForCUValidator implements IValidator <RecipeForCU> {


    @Override
    public void validation(RecipeForCU recipeForCU) {
        MultipleErrorResponse multipleError = new MultipleErrorResponse();

        if(recipeForCU.getTitle() == null || recipeForCU.getTitle().isBlank()){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "title"));
        }
        if(recipeForCU.getTitle().length() > 255){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Значение поля превышает 255 символов.", "title"));
        }
        if(recipeForCU.getComposition() == null || recipeForCU.getComposition().size() == 0){
            if(multipleError.getLogref() == null){
                multipleError.setLogref("structured_error");
            }
            multipleError.setErrors(new MyError("Поле не заполнено.", "composition"));
        }
        List<Ingredient> ingredientEntityList = recipeForCU.getComposition();
        for (Ingredient ingredient : ingredientEntityList) {
            if(ingredient.getWeight() == 0){
                if(multipleError.getLogref() == null){
                    multipleError.setLogref("structured_error");
                }
                multipleError.setErrors(new MyError("Поле не может быть равно 0.", "weight"));
            }
            if(ingredient.getWeight() < 0){
                if(multipleError.getLogref() == null){
                    multipleError.setLogref("structured_error");
                }
                multipleError.setErrors(new MyError("Поле не может быть отрицательным.", "weight"));
            }
            if(ingredient.getProduct() == null){
                if(multipleError.getLogref() == null){
                    multipleError.setLogref("structured_error");
                }
                multipleError.setErrors(new MyError("Поле не заполнено.", "product"));
            }
            if(ingredient.getProduct().getUuid() == null){
                if(multipleError.getLogref() == null){
                    multipleError.setLogref("structured_error");
                }
                multipleError.setErrors(new MyError("Поле не заполнено.", "product"));
            }
        }
        if(multipleError.getErrors().size()>0){
            throw multipleError;
        }
    }
}
