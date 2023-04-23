package by.it_academy.fitness.core.dto;
import by.it_academy.fitness.service.converters.json.LocalDateTimeToLongSerializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


public class Audit {
    @JsonProperty ("uuid")
    private UUID uuid;

    @JsonSerialize(using = LocalDateTimeToLongSerializer.class)
    @JsonProperty ("dt_create")
    private LocalDateTime dtCreate;
    @JsonProperty ("user")
    private User user;
    @JsonProperty ("text")
    private String text;
    @JsonProperty ("type")
    private EssenceType type;
    @JsonProperty ("id")
    private String id;
    public Audit() {
    }

    public Audit(UUID uuid, LocalDateTime dtCreate, User user, String text, EssenceType type, String id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
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
        return Objects.equals(uuid, audit.uuid) && Objects.equals(dtCreate, audit.dtCreate) && Objects.equals(user, audit.user) && Objects.equals(text, audit.text) && type == audit.type && Objects.equals(id, audit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, user, text, type, id);
    }

    @Override
    public String toString() {
        return "Audit{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}
