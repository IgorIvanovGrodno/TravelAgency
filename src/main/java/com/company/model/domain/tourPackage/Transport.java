package com.company.model.domain.tourPackage;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * This class contains information about transport.
 *
 * @author Igor Ivanov
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "transport")
public class Transport implements Serializable
{

    /**
     * This field is serial version identifier for serialization.
     */
    private static final long SERIAL_VERSION_UID = 24L;

    /**
     * This field is identifier for hibernate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field is name of transport.
     */
    @Column
    private String name;

    /**
     * This field is list of tour packages that have this transport.
     */
    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private List<TourPackage> tourPackage;

    /**
     * Default constructor.
     */
    public Transport()
    {
    }

    /**
     * This method return name of transport.
     *
     * @return name of transport.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method set name of transport.
     *
     * @param name - name of transport.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method return list of tour packages that have this transport.
     *
     * @return list of tour packages that have this transport.
     */
    public List<TourPackage> getTourPackage()
    {
        return tourPackage;
    }

    /**
     * This method set list of tour packages that have this transport.
     *
     * @param tourPackage - list of tour packages that have this transport.
     */
    public void setTourPackage(List<TourPackage> tourPackage)
    {
        this.tourPackage = tourPackage;
    }

    /**
     * This method return identifier of transport.
     *
     * @return identifier of transport.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * This method set identifier of transport.
     *
     * @param id - identifier of transport.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * This method used for compare objects of transports.
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
        Transport transport = (Transport) o;
        return Objects.equals(id, transport.id) &&
                Objects.equals(name, transport.name);
    }

    /**
     * This method return hash of transport's object.
     *
     * @return hash of transport's object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }

    /**
     * This method return string representation of transport's object.
     *
     * @return string representation of transport's object.
     */
    @Override
    public String toString()
    {
        return name;
    }
}
