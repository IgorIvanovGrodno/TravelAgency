package com.company.model.domain.tourPackage;

import com.company.model.domain.order.Order;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "tour_package")
public class TourPackage implements Comparable<TourPackage>, Serializable {
    private static final long SERIAL_VERSION_UID=22L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Description shouldn't be empty!")
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "type")
    private TypeTourPackage type;

    @ManyToOne
    @JoinColumn(name = "food_system")
    private FoodSystem foodSystem;

    @ManyToOne
    @JoinColumn(name = "transport")
    private Transport transport;

    @Column
    private int days;
    @Column
    private int price;
    @Column(name = "status")
    private boolean statusHot;

    @OneToMany(mappedBy="tourPackage")
    private Set<Order> orders;

    public TourPackage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeTourPackage getType() {
        return type;
    }

    public void setType(TypeTourPackage type) {
        this.type = type;
    }

    public FoodSystem getFoodSystem() {
        return foodSystem;
    }

    public void setFoodSystem(FoodSystem foodSystem) {
        this.foodSystem = foodSystem;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isStatusHot() {
        return statusHot;
    }

    public void setStatusHot(boolean statusHot) {
        this.statusHot = statusHot;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, foodSystem, transport, days, price, statusHot);
    }

    @Override
    public String toString() {
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

    @Override
    public int compareTo(TourPackage p) {
        int resultCompareType = type.toString().compareTo(p.getType().toString());
        if (resultCompareType == 0) return price - p.getPrice();
        else return resultCompareType;
    }
}
