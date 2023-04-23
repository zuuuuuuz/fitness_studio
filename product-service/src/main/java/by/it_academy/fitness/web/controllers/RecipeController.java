package by.it_academy.fitness.web.controllers;


import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.RecipeForCU;
import by.it_academy.fitness.service.api.IRecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final IRecipeService service;

    public RecipeController(IRecipeService service) {
            this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody RecipeForCU recipeForCU){
        service.add(recipeForCU);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page> getPageRecipes(@RequestParam (name = "page", defaultValue = "0") Integer page,
                                                @RequestParam (name = "size", defaultValue = "20") Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPageRecipes(page, size));
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable ("uuid") UUID uuid,
                                           @PathVariable ("dt_update") LocalDateTime dtUpdate,//должно быть LocalDateTime, dtUpdate
                                           @RequestBody RecipeForCU recipeForCU){
        service.updateRecipe(uuid, dtUpdate, recipeForCU);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
