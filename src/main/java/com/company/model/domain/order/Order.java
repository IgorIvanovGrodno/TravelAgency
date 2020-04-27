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

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "client_order")
public class Order implements Serializable{
    private static final long SERIAL_VERSION_UID=11L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\d{4}", message = "Please, enter correct number of card (4 digits)")
    @Column(name = "number_card")
    private String numberCard;

    @Column(name = "total_cost")
    private Double totalCost;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tour_package_id")
    private TourPackage tourPackage;

    @ManyToOne
    @JoinColumn(name = "status")
    private StatusOrder status;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public Order() {
    }

    public Order(String numberCard, Double totalCost) {
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
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

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
                Objects.equals(tourPackage, order.tourPackage) &&
                Objects.equals(status, order.status) &&
                Objects.equals(createDate, order.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberCard, totalCost, user, tourPackage, status, createDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", numberCard='" + numberCard + '\'' +
                ", totalCost=" + totalCost +
                ", user=" + user +
                ", tourPackage=" + tourPackage +
                ", status=" + status +
                ", createDate=" + createDate +
                '}';
    }
}
