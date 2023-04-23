package by.it_academy.fitness.core.dto;

import java.util.Objects;

public class UserRegistration {
    private String mail;
    private String fio;
    private String password;

    public UserRegistration(String mail, String fio, String password) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
    }

    public UserRegistration() {
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistration that = (UserRegistration) o;
        return Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, fio, password);
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
