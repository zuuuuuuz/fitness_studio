package by.it_academy.fitness.entity;

import by.it_academy.fitness.core.dto.UserStatus;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;

    public StatusEntity() {
    }

    public StatusEntity(UserStatus status) {
        this.status = status;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusEntity that = (StatusEntity) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "StatusEntity{" +
                "status=" + status +
                '}';
    }
}
