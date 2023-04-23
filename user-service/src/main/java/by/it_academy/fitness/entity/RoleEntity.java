package by.it_academy.fitness.entity;

import by.it_academy.fitness.core.dto.UserRole;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name ="role")
    private UserRole role;

    public RoleEntity() {
    }

    public RoleEntity(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }

    @Override
    public String toString() {
        return "RolesEntity{" +
                "role=" + role +
                '}';
    }
}
