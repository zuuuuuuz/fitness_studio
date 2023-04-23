package by.it_academy.fitness.core.dto;

import by.it_academy.fitness.service.converters.json.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    @JsonProperty ("dt_create")
    private LocalDateTime dtCreate;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    @JsonProperty ("dt_update")
    private LocalDateTime dtUpdate;
    @JsonProperty ("mail")
    private String mail;
    @JsonProperty ("fio")
    private String fio;
    @JsonProperty ("role")
    private UserRole role;
    @JsonProperty ("status")
    private UserStatus status;

    public User() {
    }

    public User(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String mail,
                String fio, UserRole role, UserStatus status) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
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

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uuid, user.uuid) && Objects.equals(dtCreate, user.dtCreate) && Objects.equals(dtUpdate, user.dtUpdate) && Objects.equals(mail, user.mail) && Objects.equals(fio, user.fio) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, mail, fio, role, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", dt_create=" + dtCreate +
                ", dt_update=" + dtUpdate +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
