package by.it_academy.fitness.core.entity;

import by.it_academy.fitness.core.dto.EssenceType;
import by.it_academy.fitness.core.dto.UserRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "audits",uniqueConstraints={@UniqueConstraint(columnNames={"uuid"})})
public class AuditEntity {

    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "user_uuid")
    private UUID userUUID;
    @Column(name = "user_mail")
    private String userMail;
    @Column(name = "user_fio")
    private String userFio;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Column(name = "text")
    private String text;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EssenceType type;
    @Column(name = "id")
    private String id;
    public AuditEntity() {
    }

    public AuditEntity(UUID uuid, LocalDateTime dtCreate, UUID userUUID, String userMail, String userFio, UserRole userRole, String text, EssenceType type, String id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.userUUID = userUUID;
        this.userMail = userMail;
        this.userFio = userFio;
        this.userRole = userRole;
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

    public UUID getUserUUID() {
        return userUUID;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserFio() {
        return userFio;
    }

    public UserRole getUserRole() {
        return userRole;
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

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void setUserFio(String userFio) {
        this.userFio = userFio;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
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
        AuditEntity audit = (AuditEntity) o;
        return Objects.equals(uuid, audit.uuid) && Objects.equals(dtCreate, audit.dtCreate) && Objects.equals(userUUID, audit.userUUID) && Objects.equals(userMail, audit.userMail) && Objects.equals(userFio, audit.userFio) && userRole == audit.userRole && Objects.equals(text, audit.text) && type == audit.type && Objects.equals(id, audit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, userUUID, userMail, userFio, userRole, text, type, id);
    }

    @Override
    public String toString() {
        return "Audit{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", userUUID=" + userUUID +
                ", userMail='" + userMail + '\'' +
                ", userFio='" + userFio + '\'' +
                ", userRole=" + userRole +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}
