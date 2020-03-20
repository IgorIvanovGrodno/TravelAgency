package com.company.model.domain.TourPackage;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tour_package")
public class TourPackage implements Comparable<TourPackage>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private TourPackageType type;

    @Column(name = "food_system")
    @Enumerated(EnumType.STRING)
    private FoodSystem foodSystem;
    @Column
    @Enumerated(EnumType.STRING)
    private Transport transport;
    @Column
    private int days;
    @Column
    private int price;
    @Column(name = "status")
    private boolean statusHot;

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

    public TourPackageType getType() {
        return type;
    }

    public void setType(TourPackageType type) {
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
