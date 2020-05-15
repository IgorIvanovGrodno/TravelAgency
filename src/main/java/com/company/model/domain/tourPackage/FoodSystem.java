package com.company.model.domain.tourPackage;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * This class contains information about food system.
 *
 * @author Igor Ivanov
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "food_system")
public class FoodSystem implements Serializable
{

    /**
     * This field is serial version identifier for serialization.
     */
    private static final long SERIAL_VERSION_UID = 21L;

    /**
     * This field is identifier for hibernate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field is name of food system.
     */
    @Column
    private String name;

    /**
     * This field is list of tour packages that have this food system.
     */
    @OneToMany(mappedBy = "foodSystem", cascade = CascadeType.ALL)
    private List<TourPackage> tourPackage;

    /**
     * Default constructor.
     */
    public FoodSystem()
    {
    }

    /**
     * This method return name of food system.
     *
     * @return name of food system.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method set name of food system.
     *
     * @param name - name of food system.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method return list of tour packages that have this food system.
     *
     * @return list of tour packages that have this food system.
     */
    public List<TourPackage> getTourPackage()
    {
        return tourPackage;
    }

    /**
     * This method set list of tour packages that have this food system.
     *
     * @param tourPackage - list of tour packages that have this food system.
     */
    public void setTourPackage(List<TourPackage> tourPackage)
    {
        this.tourPackage = tourPackage;
    }

    /**
     * This method return identifier of food system.
     *
     * @return identifier of food system.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * This method set identifier of food system.
     *
     * @param id - identifier of food system.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * This method used for compare objects of food systems.
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
        FoodSystem that = (FoodSystem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    /**
     * This method return hash of food system's object.
     *
     * @return hash of food system's object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }

    /**
     * This method return string representation of food system's object.
     *
     * @return string representation of food system's object.
     */
    @Override
    public String toString()
    {
        return name;
    }
}
