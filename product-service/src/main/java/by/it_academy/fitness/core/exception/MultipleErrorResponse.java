package by.it_academy.fitness.core.exception;

import java.util.ArrayList;
import java.util.List;

public class MultipleErrorResponse extends RuntimeException{

    private String logref;

    private List<MyError> errors = new ArrayList<>();

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    public List<MyError> getErrors() {
        return errors;
    }

    public void setErrors(List<MyError> errors) {
        this.errors = errors;
    }

    public void setErrors(MyError error) {
        this.errors.add(error);
    }
}
