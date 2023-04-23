package by.it_academy.fitness.core.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Ingredient {
    private Product product;

    private int weight;

    private BigDecimal calories;
    private BigDecimal proteins;
    private BigDecimal fats;
    private BigDecimal carbohydrates;

    public Ingredient(Product product, int weight, BigDecimal calories, BigDecimal proteins, BigDecimal fats, BigDecimal carbohydrates) {
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public Product getProduct() {
        return product;
    }

    public int getWeight() {
        return weight;
    }

    public BigDecimal getCalories() {
        return calories;
    }

    public BigDecimal getProteins() {
        return proteins;
    }

    public BigDecimal getFats() {
        return fats;
    }

    public BigDecimal getCarbohydrates() {
        return carbohydrates;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCalories(BigDecimal calories) {
        this.calories = calories;
    }

    public void setProteins(BigDecimal proteins) {
        this.proteins = proteins;
    }

    public void setFats(BigDecimal fats) {
        this.fats = fats;
    }

    public void setCarbohydrates(BigDecimal carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return weight == that.weight && calories == that.calories && Objects.equals(product, that.product) && Objects.equals(proteins, that.proteins) && Objects.equals(fats, that.fats) && Objects.equals(carbohydrates, that.carbohydrates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, weight, calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "product=" + product +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
