package by.it_academy.fitness.core.audit;

import by.it_academy.fitness.core.dto.EssenceType;
import by.it_academy.fitness.core.dto.User;

import java.util.Objects;

public class Audit {
    private User user;
    private String text;
    private EssenceType type;
    private String id;

    public Audit() {
    }

    public Audit(User user, String text, EssenceType type, String id) {
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public User getUser() {
        return user;
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

    public void setUser(User user) {
        this.user = user;
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
        Audit audit = (Audit) o;
        return Objects.equals(user, audit.user) && Objects.equals(text, audit.text) && type == audit.type && Objects.equals(id, audit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, text, type, id);
    }

    @Override
    public String toString() {
        return "Audit{" +
                "user=" + user +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}
