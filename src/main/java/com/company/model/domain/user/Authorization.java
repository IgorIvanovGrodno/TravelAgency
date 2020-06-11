package com.company.model.domain.user;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * This class contains information about authorization.
 *
 * @author Igor Ivanov
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "authorization")
public class Authorization implements Serializable
{
    /**
     * This field is serial version identifier for serialization.
     */
    private static final long SERIAL_VERSION_UID = 31L;

    /**
     * This field is login and identifier for hibernate.
     */
    @Id
    private String login;

    /**
     * This field is password.
     */
    @Column
    private String password;

    /**
     * This field is role.
     */
    @Column
    private String role;

    /**
     * This field is status person(active/no active).
     */
    @Column
    private boolean active;

    /**
     * This field is user which associated with the authorization.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User user;

    /**
     * Default constructor.
     */
    public Authorization()
    {
    }

    /**
     * This method return login.
     *
     * @return login.
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * This method set login.
     *
     * @param login - login.
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * This method return password.
     *
     * @return password.
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * This method set password.
     *
     * @param password - password.
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * This method return role.
     *
     * @return role.
     */
    public String getRole()
    {
        return role;
    }

    /**
     * This method set role.
     *
     * @param role - role.
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    /**
     * This method return user which associated with the authorization.
     *
     * @return user which associated with the authorization.
     */
    public User getUser()
    {
        return user;
    }

    /**
     * This method set user which associated with the authorization.
     *
     * @param user - user which associated with the authorization.
     */
    public void setUser(User user)
    {
        this.user = user;
    }

    /**
     * This method return status person(active/no active).
     *
     * @return status person(active/no active).
     */
    public boolean isActive()
    {
        return active;
    }

    /**
     * This method set status person(active/no active).
     *
     * @param active - status person(active/no active).
     */
    public void setActive(boolean active)
    {
        this.active = active;
    }

    /**
     * This method used for compare objects of authorization.
     *
     * @param o - another object.
     * @return true if objects is equals, else - false.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Authorization authorization1 = (Authorization) o;
        return Objects.equals(login, authorization1.login) &&
                Objects.equals(password, authorization1.password) &&
                Objects.equals(role, authorization1.role) &&
                Objects.equals(user, authorization1.user);
    }

    /**
     * This method return hash of authorization's object.
     *
     * @return hash of authorization's object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(login, password, role, user);
    }

    /**
     * This method return string representation of authorization's object.
     *
     * @return string representation of authorization's object.
     */
    @Override
    public String toString()
    {
        return "Login{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", user=" + user +
                '}';
    }
}
