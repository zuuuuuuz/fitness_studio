package by.it_academy.fitness.core.dto;

import by.it_academy.fitness.service.converters.json.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Product {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    @JsonProperty("dt_create")
    private LocalDateTime dtCreate;
    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    @JsonProperty("dt_update")
    private LocalDateTime dtUpdate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("weight")
    private int weight;
    @JsonProperty("calories")
    private int calories;
    @JsonProperty("proteins")
    private double proteins;
    @JsonProperty("fats")
    private double fats;
    @JsonProperty("carbohydrates")
    private double carbohydrates;

    public Product() {
    }

    public Product(UUID uuid,
                   LocalDateTime dtCreate,
                   LocalDateTime dtUpdate,
                   String title,
                   int weight,
                   int calories,
                   double proteins,
                   double fats,
                   double carbohydrates) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public int getWeight() {
        return weight;
    }

    public int getCalories() {
        return calories;
    }

    public double getProteins() {
        return proteins;
    }

    public double getFats() {
        return fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return weight == product.weight && calories == product.calories && Double.compare(product.proteins, proteins) == 0 && Double.compare(product.fats, fats) == 0 && Double.compare(product.carbohydrates, carbohydrates) == 0 && Objects.equals(uuid, product.uuid) && Objects.equals(dtCreate, product.dtCreate) && Objects.equals(dtUpdate, product.dtUpdate) && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title, weight, calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "Product{" +
                "uuid=" + uuid +
                ", dt_create=" + dtCreate +
                ", dt_update=" + dtUpdate +
                ", title='" + title + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }

    public  static class ProductBuilder{
        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;
        private int weight;
        private int calories;
        private double proteins;
        private double fats;
        private double carbohydrates;

        private ProductBuilder() {
        }
        public static ProductBuilder create(){
            return new ProductBuilder();
        }

        public ProductBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public ProductBuilder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public ProductBuilder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public ProductBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ProductBuilder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public ProductBuilder setCalories(int calories) {
            this.calories = calories;
            return this;
        }

        public ProductBuilder setProteins(double proteins) {
            this.proteins = proteins;
            return this;
        }

        public ProductBuilder setFats(double fats) {
            this.fats = fats;
            return this;
        }

        public ProductBuilder setCarbohydrates(double carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }

        public Product build(){
            return new Product(uuid, dtCreate, dtUpdate, title, weight, calories, proteins, fats, carbohydrates);
        }
    }

}
