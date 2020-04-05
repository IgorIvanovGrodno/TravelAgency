package com.company.model.domain.user;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "authorization")
public class Login {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;*/

    @Id
    private String login;

    @OneToOne
    @JoinColumn(name = "client_id")
    private User user;

    public Login() {
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return Objects.equals(login, login1.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return "Login{" +
                "login='" + login + '\'' +
                '}';
    }
}
