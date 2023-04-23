package by.it_academy.fitness.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.Objects;

@Entity
@Table(name = "ingredients")

public class IngredientEntity {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private ProductEntity product;
    @Column(name = "weight")
    private int weight;

    public IngredientEntity() {
    }

    public IngredientEntity(ProductEntity product, int weight) {
        this.product = product;
        this.weight = weight;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public int getWeight() {
        return weight;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientEntity that = (IngredientEntity) o;
        return weight == that.weight && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, weight);
    }

    @Override
    public String toString() {
        return "IngredientEntity{" +
                "product=" + product +
                ", weight=" + weight +
                '}';
    }
}
