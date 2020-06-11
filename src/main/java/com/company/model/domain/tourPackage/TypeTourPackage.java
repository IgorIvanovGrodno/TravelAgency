package com.company.model.domain.tourPackage;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * This class contains information about type of tour package.
 *
 * @author Igor Ivanov
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "tour_type")
public class TypeTourPackage implements Serializable
{

    /**
     * This field is serial version identifier for serialization.
     */
    private static final long SERIAL_VERSION_UID = 23L;

    /**
     * This field is identifier for hibernate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field is name of tour package's type.
     */
    @Column
    private String name;

    /**
     * This field is list of tour packages that have this type.
     */
    @OneToMany(mappedBy = "type")
    private List<TourPackage> tourPackage;

    /**
     * Default constructor.
     */
    public TypeTourPackage()
    {
    }

    /**
     * This method return list of tour packages that have this type.
     *
     * @return list of tour packages that have this type.
     */
    public List<TourPackage> getTourPackage()
    {
        return tourPackage;
    }

    /**
     * This method set list of tour packages that have this type.
     *
     * @param tourPackage - list of tour packages that have this type.
     */
    public void setTourPackage(List<TourPackage> tourPackage)
    {
        this.tourPackage = tourPackage;
    }

    /**
     * This method return name of tour package's type.
     *
     * @return name of tour package's type.
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method set name of tour package's type.
     *
     * @param name - name of tour package's type.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * This method return identifier of tour package's type.
     *
     * @return identifier of tour package's type.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * This method set identifier of tour package's type.
     *
     * @param id - identifier of tour package's type.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * This method used for compare objects of tour package's type.
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
        TypeTourPackage that = (TypeTourPackage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    /**
     * This method return hash of tour package's type object.
     *
     * @return hash of tour package's type object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }

    /**
     * This method return string representation of tour package's type object.
     *
     * @return string representation of tour package's type object.
     */
    @Override
    public String toString()
    {
        return name;
    }
}
