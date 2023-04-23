package by.it_academy.fitness.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users",
        uniqueConstraints =
                {
                        @UniqueConstraint(columnNames = "uuid"),
                        @UniqueConstraint(columnNames = "mail")
                })
public class UserCreateEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "fio")
    private String fio;

    @Column(name = "dt_create")
    @NotNull
    private LocalDateTime dtCreate;

    @Column(name = "dt_update")
    @NotNull
    @Version
    private LocalDateTime dtUpdate;

    @Column(name = "mail")
    @NotNull
    private String mail;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role")
    @NotNull
    private RoleEntity role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "status")
    @NotNull
    private StatusEntity status;

    @Column(name = "password")
    @NotNull
    private String password;

    public UserCreateEntity() {
    }

    public UserCreateEntity(UUID uuid,
                            String fio,
                            LocalDateTime dtCreate,
                            LocalDateTime dtUpdate,
                            String mail,
                            RoleEntity role,
                            StatusEntity status,
                            String password) {
        this.uuid = uuid;
        this.fio = fio;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getFio() {
        return fio;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public RoleEntity getRole() {
        return role;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateEntity that = (UserCreateEntity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(fio, that.fio)
                && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate)
                && Objects.equals(mail, that.mail) && role == that.role
                && status == that.status && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fio, dtCreate, dtUpdate, mail, role, status, password);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uuid=" + uuid +
                ", fio='" + fio + '\'' +
                ", dt_create=" + dtCreate +
                ", dt_update=" + dtUpdate +
                ", mail='" + mail + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
