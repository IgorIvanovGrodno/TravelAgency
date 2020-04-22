package com.company.model.domain.order;

import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "client_order")
public class Order implements Serializable{
    private static final long SERIAL_VERSION_UID=7L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\d{4}", message = "Please, enter correct number of card (4 digits)")
    @Column(name = "number_card")
    private String numberCard;

    @Column(name = "total_cost")
    private Long totalCost;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_package_id")
    private TourPackage tourPackage;

    public Order() {
    }

    public Order(String numberCard, Long totalCost) {
        this.numberCard = numberCard;
        this.totalCost = totalCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public Long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Long totalCost) {
        this.totalCost = totalCost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TourPackage getTourPackage() {
        return tourPackage;
    }

    public void setTourPackage(TourPackage tourPackage) {
        this.tourPackage = tourPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(numberCard, order.numberCard) &&
                Objects.equals(totalCost, order.totalCost) &&
                Objects.equals(user, order.user) &&
                Objects.equals(tourPackage, order.tourPackage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberCard, totalCost, user, tourPackage);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", numberCard='" + numberCard + '\'' +
                ", totalCost=" + totalCost +
                ", user=" + user +
                ", tourPackage=" + tourPackage +
                '}';
    }
}
