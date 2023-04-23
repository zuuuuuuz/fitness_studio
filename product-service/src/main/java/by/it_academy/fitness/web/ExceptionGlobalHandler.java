package by.it_academy.fitness.web;

import by.it_academy.fitness.core.dto.MultipleError;
import by.it_academy.fitness.core.exception.Error;
import by.it_academy.fitness.core.exception.MultipleErrorResponse;
import by.it_academy.fitness.core.exception.SingleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler()
    public ResponseEntity<List<Error>> handlerSingle(SingleErrorResponse e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrors());
    }

    @ExceptionHandler
    public ResponseEntity<MultipleError> handlerMulti(MultipleErrorResponse e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MultipleError(e.getLogref(), e.getErrors()));
    }

    @ExceptionHandler
    public ResponseEntity<List<Error>> handlerNPE(Throwable e){
        List<Error> errors = new ArrayList<>();
        errors.add(new Error("error", "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }
}
