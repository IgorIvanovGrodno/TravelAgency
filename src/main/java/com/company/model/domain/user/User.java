package com.company.model.domain.user;

import com.company.model.domain.tourPackage.TourPackage;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "client")
public class User implements Serializable {
    private static final long SERIAL_VERSION_UID=1l;

    @NotNull(message = "Please, select user for set discount!")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int discount;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Login login;

    @ManyToMany( fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "client_tour",
            joinColumns = { @JoinColumn(name = "client_id") },
            inverseJoinColumns = { @JoinColumn(name = "tour_package_id") }
    )
    private List<TourPackage> paidTourPackages = new ArrayList<>();

    public User() {
    }

    public List<TourPackage> getPaidTourPackages() {
        return paidTourPackages;
    }

    public void setPaidTourPackages(List<TourPackage> paidTourPackages) {
        this.paidTourPackages = paidTourPackages;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", discount=" + discount +
                ", login=" +
                login +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return discount == user.discount &&
                Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discount);
    }
}
