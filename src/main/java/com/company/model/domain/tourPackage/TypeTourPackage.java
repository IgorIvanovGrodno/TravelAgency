package com.company.model.domain.tourPackage;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tour_type")
public class TypeTourPackage implements Serializable {
    private static final long SERIAL_VERSION_UID=23L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<TourPackage> tourPackage;

    public TypeTourPackage() {
    }

    public List<TourPackage> getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(List<TourPackage> tourPackage) {
        this.tourPackage = tourPackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeTourPackage that = (TypeTourPackage) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return  name;
    }
}
