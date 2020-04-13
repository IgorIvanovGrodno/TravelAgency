package com.company.model.domain.user;

import com.company.model.domain.tourPackage.TourPackage;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "client")
public class User implements Serializable {
    private static final long SERIAL_VERSION_UID=1L;

  //  @NotNull(message = "Please, select user for set discount!")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int discount;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Authorization authorization;

    @ManyToMany( fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "client_tour",
            joinColumns = { @JoinColumn(name = "client_id") },
            inverseJoinColumns = { @JoinColumn(name = "tour_package_id") }
    )
    private List<TourPackage> paidTourPackages = new ArrayList<>();

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TourPackage> getPaidTourPackages() {
        return paidTourPackages;
    }

    public void setPaidTourPackages(List<TourPackage> paidTourPackages) {
        this.paidTourPackages = paidTourPackages;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return discount == user.discount &&
                Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(email, user.email) &&
                Objects.equals(authorization, user.authorization) &&
                Objects.equals(paidTourPackages, user.paidTourPackages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discount, firstName, secondName, phoneNumber, email, authorization, paidTourPackages);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", discount=" + discount +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", login=" + authorization +
                ", paidTourPackages=" + paidTourPackages +
                '}';
    }
}
