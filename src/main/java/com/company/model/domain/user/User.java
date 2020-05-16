package com.company.model.domain.user;

import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * This class contains information about user.
 *
 * @author Igor Ivanov
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "client")
public class User implements Serializable
{
    /**
     * This field is serial version identifier for serialization.
     */
    private static final long SERIAL_VERSION_UID = 32L;

    /**
     * This field is identifier for hibernate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field is user's discount.
     */
    @Column
    private int discount;

    /**
     * This field is user's first name.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * This field is user's second name.
     */
    @Column(name = "second_name")
    private String secondName;

    /**
     * This field is user's phone number.
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * This field is user's E-mail.
     */
    @Column
    private String email;

    /**
     * This field is user's authorization information.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Authorization authorization;

    /**
     * This field is user's orders.
     */
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    /**
     * Default constructor.
     */
    public User()
    {
    }

    /**
     * This method return user's first name.
     *
     * @return user's first name.
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * This method set user's first name.
     *
     * @param firstName - user's first name.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * This method return user's second name.
     *
     * @return user's second name.
     */
    public String getSecondName()
    {
        return secondName;
    }

    /**
     * This method set user's second name.
     *
     * @param secondName - user's second name.
     */
    public void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

    /**
     * This method return user's phone number.
     *
     * @return user's phone number.
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * This method set user's phone number.
     *
     * @param phoneNumber - user's phone number.
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method return user's E-mail.
     *
     * @return user's E-mail.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * This method set user's E-mail.
     *
     * @param email - user's E-mail.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * This method return user's authorization information.
     *
     * @return user's authorization information.
     */
    public Authorization getAuthorization()
    {
        return authorization;
    }

    /**
     * This method set user's authorization information.
     *
     * @param authorization - user's authorization information.
     */
    public void setAuthorization(Authorization authorization)
    {
        this.authorization = authorization;
    }

    /**
     * This method return identifier for hibernate.
     *
     * @return identifier for hibernate.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * This method set identifier for hibernate.
     *
     * @param id - identifier for hibernate.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * This method return user's discount.
     *
     * @return user's discount.
     */
    public int getDiscount()
    {
        return discount;
    }

    /**
     * This method set user's discount.
     *
     * @param discount - user's discount.
     */
    public void setDiscount(int discount)
    {
        this.discount = discount;
    }

    /**
     * This method return user's orders.
     *
     * @return user's orders.
     */
    public List<Order> getOrders()
    {
        return orders;
    }

    /**
     * This method set user's orders.
     *
     * @param orders - user's orders.
     */
    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

    /**
     * This method used for compare objects of users.
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
        User user = (User) o;
        return discount == user.discount &&
                Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(email, user.email) &&
                Objects.equals(authorization, user.authorization) &&
                Objects.equals(orders, user.orders);
    }

    /**
     * This method return hash of user's object.
     *
     * @return hash of user's object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id, discount, firstName, secondName, phoneNumber, email, authorization, orders);
    }

    /**
     * This method return string representation of user's object.
     *
     * @return string representation of user's object.
     */
    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", discount=" + discount +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", orders=" + orders +
                '}';
    }
}
