package by.it_academy.fitness.core.exception;

import java.util.ArrayList;
import java.util.List;

public class SingleErrorResponse extends RuntimeException {

    private List<Error> errors = new ArrayList<>();

    public SingleErrorResponse(String logref, String message) {
        this.errors.add(new Error(logref, message));
    }
    public SingleErrorResponse(String message) {
        this.errors.add(new Error(message));
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public void setErrors(Error error) {
        this.errors.add(error);
    }
}
