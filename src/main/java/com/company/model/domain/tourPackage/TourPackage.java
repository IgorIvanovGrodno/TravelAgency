package com.company.model.domain.tourPackage;

import com.company.model.domain.order.Order;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * This class contains information about tour package.
 *
 * @author Igor Ivanov
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tour_package")
public class TourPackage implements Serializable
{
    /**
     * This field is serial version identifier for serialization.
     */
    private static final long SERIAL_VERSION_UID = 22L;

    /**
     * This field is identifier for hibernate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field is description of tour package.
     */
    @Column
    private String name;

    /**
     * This field is type of tour package.
     */
    @ManyToOne
    @JoinColumn(name = "type")
    private TypeTourPackage type;

    /**
     * This field is food system of tour package.
     */
    @ManyToOne
    @JoinColumn(name = "food_system")
    private FoodSystem foodSystem;

    /**
     * This field is transport of tour package.
     */
    @ManyToOne
    @JoinColumn(name = "transport")
    private Transport transport;

    /**
     * This field is amount of days.
     */
    @Column
    private int days;

    /**
     * This field is price of tour package.
     */
    @Column
    private int price;

    /**
     * This field is status(hot/no hot) of tour package.
     */
    @Column(name = "status")
    private boolean statusHot;

    /**
     * This field is set of orders that contain this tour package.
     */
    @OneToMany(mappedBy = "tourPackage")
    private Set<Order> orders;

    /**
     * Default constructor.
     */
    public TourPackage()
    {
    }

    /**
     * This method return identifier of tour package.
     *
     * @return identifier of tour package.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * This method set identifier of tour package.
     *
     * @param id - identifier of tour package.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * This method return description of tour package.
     *
     * @return description of tour package.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method set description of tour package.
     *
     * @param name - description of tour package.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method return type of tour package.
     *
     * @return type of tour package.
     */
    public TypeTourPackage getType()
    {
        return type;
    }

    /**
     * This method set type of tour package.
     *
     * @param type - type of tour package.
     */
    public void setType(TypeTourPackage type)
    {
        this.type = type;
    }

    /**
     * This method return food system of tour package.
     *
     * @return food system of tour package.
     */
    public FoodSystem getFoodSystem()
    {
        return foodSystem;
    }

    /**
     * This method set food system of tour package.
     *
     * @param foodSystem - food system of tour package.
     */
    public void setFoodSystem(FoodSystem foodSystem)
    {
        this.foodSystem = foodSystem;
    }

    /**
     * This method return transport of tour package.
     *
     * @return transport of tour package.
     */
    public Transport getTransport()
    {
        return transport;
    }

    /**
     * This method set transport of tour package.
     *
     * @param transport - transport of tour package.
     */
    public void setTransport(Transport transport)
    {
        this.transport = transport;
    }

    /**
     * This method return amount of days.
     *
     * @return amount of days.
     */
    public int getDays()
    {
        return days;
    }

    /**
     * This method set amount of days.
     *
     * @param days - amount of days.
     */
    public void setDays(int days)
    {
        this.days = days;
    }

    /**
     * This method return price of tour package.
     *
     * @return price of tour package.
     */
    public int getPrice()
    {
        return price;
    }

    /**
     * This method set price of tour package.
     *
     * @param price - price of tour package.
     */
    public void setPrice(int price)
    {
        this.price = price;
    }

    /**
     * This method return status(hot/no hot) of tour package.
     *
     * @return status(hot / no hot) of tour package.
     */
    public boolean isStatusHot()
    {
        return statusHot;
    }

    /**
     * This method set status(hot/no hot) of tour package.
     *
     * @param statusHot - status(hot/no hot) of tour package.
     */
    public void setStatusHot(boolean statusHot)
    {
        this.statusHot = statusHot;
    }

    /**
     * This method return set of orders that contain this tour package.
     *
     * @return set of orders that contain this tour package.
     */
    public Set<Order> getOrders()
    {
        return orders;
    }

    /**
     * This method set orders that contain this tour package.
     *
     * @param orders - orders that contain this tour package.
     */
    public void setOrders(Set<Order> orders)
    {
        this.orders = orders;
    }

    /**
     * This method used for compare objects of tour package.
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
        TourPackage that = (TourPackage) o;
        return days == that.days &&
                price == that.price &&
                statusHot == that.statusHot &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                type == that.type &&
                foodSystem == that.foodSystem &&
                transport == that.transport;
    }

    /**
     * This method return hash of tour package's object.
     *
     * @return hash of tour package's object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id, name, type, foodSystem, transport, days, price, statusHot);
    }

    /**
     * This method return string representation of tour package.
     *
     * @return string representation of tour package.
     */
    @Override
    public String toString()
    {
        return "TourPackage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", foodSystem=" + foodSystem +
                ", transport=" + transport +
                ", days=" + days +
                ", price=" + price +
                ", statusHot=" + statusHot +
                '}';
    }
}
