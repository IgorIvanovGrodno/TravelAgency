package com.company.model.domain.user;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "authorization")
public class Authorization implements Serializable {
    private static final long SERIAL_VERSION_UID=31L;

    @Id
    private String login;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private boolean active;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User user;

    public Authorization() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorization authorization1 = (Authorization) o;
        return Objects.equals(login, authorization1.login) &&
                Objects.equals(password, authorization1.password) &&
                Objects.equals(role, authorization1.role) &&
                Objects.equals(user, authorization1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role, user);
    }

    @Override
    public String toString() {
        return "Login{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", user=" + user +
                '}';
    }
}
