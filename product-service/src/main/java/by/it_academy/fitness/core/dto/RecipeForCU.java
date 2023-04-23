package by.it_academy.fitness.core.dto;

import java.util.List;
import java.util.Objects;

public class RecipeForCU {

    private String title;
    private List <Ingredient> composition;

    public RecipeForCU(String title, List <Ingredient> composition) {
        this.title = title;
        this.composition = composition;
    }

    public String getTitle() {
        return title;
    }
    public List <Ingredient> getComposition() {
        return composition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeForCU that = (RecipeForCU) o;
        return Objects.equals(title, that.title) && Objects.equals(composition, that.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, composition);
    }

    @Override
    public String toString() {
        return "RecipeForCU{" +
                "title='" + title + '\'' +
                ", composition=" + composition +
                '}';
    }
}
