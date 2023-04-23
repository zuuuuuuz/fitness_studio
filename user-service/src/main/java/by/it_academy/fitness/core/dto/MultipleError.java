package by.it_academy.fitness.core.dto;

import by.it_academy.fitness.core.exception.MyError;
import java.util.List;

public class MultipleError {
    private String logref;

    private List<MyError> errors;

    public MultipleError(String logref, List<MyError> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public MultipleError() {
    }

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
}
