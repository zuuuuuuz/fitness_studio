package by.it_academy.fitness.web.controllers;


import by.it_academy.fitness.core.dto.Page;
import by.it_academy.fitness.core.dto.Product;
import by.it_academy.fitness.service.api.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final IProductService service;

    public ProductController(IProductService service) {
            this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Product product){
        service.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page> getPageProducts(@RequestParam (name = "page", defaultValue = "0") Integer page,
                                             @RequestParam (name = "size", defaultValue = "20") Integer size){
        return ResponseEntity.status(HttpStatus.OK).body(service.getPageProducts(page, size));
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable ("uuid") UUID uuid,
                                           @PathVariable ("dt_update") LocalDateTime dtUpdate,//должно быть LocalDateTime
                                           @RequestBody Product product){
        service.updateProduct(uuid, dtUpdate, product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
