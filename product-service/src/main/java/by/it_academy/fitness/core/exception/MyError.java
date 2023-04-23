package by.it_academy.fitness.core.exception;

import java.util.Objects;

public class MyError{

    private String message;

    private String filed;

    public MyError(String message, String filed) {
        this.message = message;
        this.filed = filed;
    }

    public String getMessage() {
        return message;
    }

    public String getFiled() {
        return filed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyError myError = (MyError) o;
        return Objects.equals(message, myError.message) && Objects.equals(filed, myError.filed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, filed);
    }

    @Override
    public String toString() {
        return "MyError{" +
                "message='" + message + '\'' +
                ", filed='" + filed + '\'' +
                '}';
    }
}
