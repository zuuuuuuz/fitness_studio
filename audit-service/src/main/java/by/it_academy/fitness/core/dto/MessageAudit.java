package by.it_academy.fitness.core.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


public class MessageAudit {
    private UUID uuid;
    private String mail;
    private String fio;
    private UserRole role;
    private String text;
    private EssenceType type;
    private String id;
    public MessageAudit() {
    }

    public MessageAudit(UUID uuid, String mail, String fio, UserRole role, String text, EssenceType type, String id) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.type = type;
        this.id = id;
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

    public String getText() {
        return text;
    }

    public EssenceType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setType(EssenceType type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageAudit that = (MessageAudit) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio) && role == that.role && Objects.equals(text, that.text) && type == that.type && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, mail, fio, role, text, type, id);
    }

    @Override
    public String toString() {
        return "MessageAudit{" +
                "uuid=" + uuid +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}
