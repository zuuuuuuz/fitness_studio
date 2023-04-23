package by.it_academy.fitness.core.dto;

import by.it_academy.fitness.service.converters.json.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class User {

    private UUID uuid;

    private String mail;


    private String fio;

    private UserRole role;


    public User() {
    }

    public User(UUID uuid, String mail, String fio, UserRole role) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
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

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(uuid, user.uuid) && Objects.equals(mail, user.mail) && Objects.equals(fio, user.fio) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, mail, fio, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                '}';
    }
}
