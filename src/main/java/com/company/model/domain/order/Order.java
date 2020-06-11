package com.company.model.domain.order;

import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * This class contains information about order(number of card, date, total cost, status, bought tour package).
 *
 * @author Igor Ivanov
 */

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "client_order")
public class Order implements Serializable
{
    /**
     * This field is serial version identifier for serialization.
     */
    private static final long SERIAL_VERSION_UID = 11L;

    /**
     * This field is identifier for hibernate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field is a number of payment card
     */
    @Pattern(regexp = "\\d{4}", message = "{validator.card.number}")
    @Column(name = "number_card")
    private String numberCard;

    /**
     * This field is a total cost of order.
     */
    @Column(name = "total_cost")
    private Double totalCost;

    /**
     * This field is a user who made the order.
     */
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;

    /**
     * This field is a tour package that was ordered.
     */
    @ManyToOne
    @JoinColumn(name = "tour_package_id")
    private TourPackage tourPackage;

    /**
     * This field is a status of order.
     */
    @ManyToOne
    @JoinColumn(name = "status")
    private StatusOrder status;

    /**
     * This field is a date of creation order.
     */
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    /**
     * Default constructor.
     */
    public Order()
    {
    }

    /**
     * Constructor with params
     *
     * @param numberCard - number of payment card
     * @param totalCost  - total cost of order
     */
    public Order(String numberCard, Double totalCost)
    {
        this.numberCard = numberCard;
        this.totalCost = totalCost;
    }

    /**
     * This method return identifier of order
     *
     * @return identifier of order.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * This method set identifier of order
     *
     * @param id - identifier of order
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * This method return number of payment card.
     *
     * @return number of payment card.
     */
    public String getNumberCard()
    {
        return numberCard;
    }

    /**
     * This method set number of payment card.
     *
     * @param numberCard - number of payment card.
     */
    public void setNumberCard(String numberCard)
    {
        this.numberCard = numberCard;
    }

    /**
     * This method return total cost of order.
     *
     * @return total cost of order.
     */
    public Double getTotalCost()
    {
        return totalCost;
    }

    /**
     * This method set total cost of order.
     *
     * @param totalCost -total cost of order.
     */
    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }

    /**
     * This method return user who made the order.
     *
     * @return user who made the order.
     */
    public User getUser()
    {
        return user;
    }

    /**
     * This method set user who made the order.
     *
     * @param user - user who made the order.
     */
    public void setUser(User user)
    {
        this.user = user;
    }

    /**
     * This method return tour package that was ordered.
     *
     * @return tour package that was ordered.
     */
    public TourPackage getTourPackage()
    {
        return tourPackage;
    }

    /**
     * This method set tour package that was ordered.
     *
     * @param tourPackage - tour package that was ordered.
     */
    public void setTourPackage(TourPackage tourPackage)
    {
        this.tourPackage = tourPackage;
    }

    /**
     * This method return status of order.
     *
     * @return status of order.
     */
    public StatusOrder getStatus()
    {
        return status;
    }

    /**
     * This method set status of order.
     *
     * @param status - status of order.
     */
    public void setStatus(StatusOrder status)
    {
        this.status = status;
    }

    /**
     * This method return date of creation order.
     *
     * @return date of creation order.
     */
    public Date getCreateDate()
    {
        return createDate;
    }

    /**
     * This method set date of creation order.
     *
     * @param createDate - date of creation order.
     */
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    /**
     * This method used for compare order's objects.
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
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(numberCard, order.numberCard) &&
                Objects.equals(totalCost, order.totalCost) &&
                Objects.equals(user, order.user) &&
                Objects.equals(tourPackage, order.tourPackage) &&
                Objects.equals(status, order.status) &&
                Objects.equals(createDate, order.createDate);
    }

    /**
     * This method return hash of order.
     *
     * @return hash of order's object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id, numberCard, totalCost, user, tourPackage, status, createDate);
    }

    /**
     * This method return string representation of order.
     *
     * @return string representation of order.
     */
    @Override
    public String toString()
    {
        return "Order{" +
                "id=" + id +
                ", numberCard='" + numberCard + '\'' +
                ", totalCost=" + totalCost +
                ", tourPackage=" + tourPackage +
                ", status=" + status +
                ", createDate=" + createDate +
                '}';
    }
}
