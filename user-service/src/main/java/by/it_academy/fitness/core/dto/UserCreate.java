package by.it_academy.fitness.core.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UserCreate {

    private UUID uuid;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private String mail;

    private String fio;

    private UserRole role;

    private UserStatus status;
    private String password;

    public UserCreate() {
    }

    public UserCreate(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String mail,
                      String fio, UserRole role, UserStatus status, String password) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.role = UserRole.USER;
        this.status = status;
        this.password = password;
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

    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreate that = (UserCreate) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate) && Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio) && role == that.role && status == that.status && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, mail, fio, role, status, password);
    }

    @Override
    public String toString() {
        return "UserCreate{" +
                "uuid=" + uuid +
                ", dt_create=" + dtCreate +
                ", dt_update=" + dtUpdate +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status + '\'' +
                '}';
    }
}
