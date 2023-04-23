package by.it_academy.fitness.core.dto;

import by.it_academy.fitness.service.converters.json.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Recipe {
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
    @JsonProperty("composition")
    private List<Ingredient> composition;
    @JsonProperty("weight")
    private int weight;
    @JsonProperty("calories")
    private int calories;
    @JsonProperty("proteins")
    private BigDecimal proteins;
    @JsonProperty("fats")
    private BigDecimal fats;
    @JsonProperty("carbohydrates")
    private BigDecimal carbohydrates;

    public Recipe(UUID uuid,
                  LocalDateTime dtCreate,
                  LocalDateTime dtUpdate,
                  String title,
                  List<Ingredient> composition,
                  int weight,
                  int calories,
                  BigDecimal proteins,
                  BigDecimal fats,
                  BigDecimal carbohydrates) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.composition = composition;
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
    public List<Ingredient> getComposition() {
        return composition;
    }
    public int getWeight() {
        return weight;
    }
    public int getCalories() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return dtCreate == recipe.dtCreate && dtUpdate == recipe.dtUpdate && weight == recipe.weight && calories == recipe.calories && Objects.equals(uuid, recipe.uuid) && Objects.equals(title, recipe.title) && Objects.equals(composition, recipe.composition) && Objects.equals(proteins, recipe.proteins) && Objects.equals(fats, recipe.fats) && Objects.equals(carbohydrates, recipe.carbohydrates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title, composition, weight, calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", title='" + title + '\'' +
                ", composition=" + composition +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }

    public  static class RecipeBuilder{
        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;
        private List<Ingredient> composition;
        private int weight;
        private int calories;
        private BigDecimal proteins;
        private BigDecimal fats;
        private BigDecimal carbohydrates;

        private RecipeBuilder() {
        }
        public static RecipeBuilder create(){
            return new RecipeBuilder();
        }

        public RecipeBuilder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public RecipeBuilder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public RecipeBuilder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public RecipeBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public RecipeBuilder setComposition(List<Ingredient> composition) {
            this.composition = composition;
            return this;
        }

        public RecipeBuilder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public RecipeBuilder setCalories(int calories) {
            this.calories = calories;
            return this;
        }

        public RecipeBuilder setProteins(BigDecimal proteins) {
            this.proteins = proteins;
            return this;
        }

        public RecipeBuilder setFats(BigDecimal fats) {
            this.fats = fats;
            return this;
        }

        public RecipeBuilder setCarbohydrates(BigDecimal carbohydrates) {
            this.carbohydrates = carbohydrates;
            return this;
        }

        public Recipe build(){
            return new Recipe(uuid, dtCreate, dtUpdate, title, composition, weight, calories, proteins, fats, carbohydrates);
        }
    }

}
