package com.company.model.domain.order;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * This class contains information about status order.
 *
 * @author Igor Ivanov
 */

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "status_order")
public class StatusOrder implements Serializable
{
    /**
     * This field is serial version identifier for serialization.
     */
    private static final long SERIAL_VERSION_UID = 12L;

    /**
     * This field is identifier for hibernate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field is name of order's status.
     */
    @Column
    private String name;

    /**
     * This field is list of orders that have this status.
     */
    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Order> orders;

    /**
     * Default constructor.
     */
    public StatusOrder()
    {
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
     * @param id
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * This method return name of order's status.
     *
     * @return name of order's status.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method set name of order's status.
     *
     * @param name -name of order's status.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method return list of orders that have this status.
     *
     * @return list of orders that have this status.
     */
    public List<Order> getOrders()
    {
        return orders;
    }

    /**
     * This method set list of orders that have this status.
     *
     * @param orders - list of orders that have this status.
     */
    public void setOrders(List<Order> orders)
    {
        this.orders = orders;
    }

    /**
     * This method used for compare objects of status.
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
        StatusOrder that = (StatusOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    /**
     * This method return hash of order's status.
     *
     * @return hash of order's status.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }

    /**
     * This method return string representation of status order.
     *
     * @return string representation of status order.
     */
    @Override
    public String toString()
    {
        return name;
    }
}
