package by.it_academy.fitness.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "recipes",
        uniqueConstraints = @UniqueConstraint(columnNames = "uuid"))
public class RecipeEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    @Version
    private LocalDateTime dtUpdate;
    @Column(name = "title")
    private String title;

    @Column(name = "composition")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="recipe_ingredient",
            joinColumns=
            @JoinColumn(name="recipe_UUID", referencedColumnName="UUID"),
            inverseJoinColumns=
            @JoinColumn(name="ingredient_ID", referencedColumnName="ID")
    )
    private List<IngredientEntity> composition;

    public RecipeEntity() {
    }

    public RecipeEntity(UUID uuid,
                        LocalDateTime dtCreate,
                        LocalDateTime dtUpdate,
                        String title,
                        List<IngredientEntity> composition) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.composition = composition;
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

    public List<IngredientEntity> getComposition() {
        return composition;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDt_create(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDt_update(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComposition(List<IngredientEntity> composition) {
        this.composition = composition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeEntity that = (RecipeEntity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate) && Objects.equals(title, that.title) && Objects.equals(composition, that.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title, composition);
    }

    @Override
    public String toString() {
        return "RecipeEntity{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", title='" + title + '\'' +
                ", composition=" + composition +
                '}';
    }
}
