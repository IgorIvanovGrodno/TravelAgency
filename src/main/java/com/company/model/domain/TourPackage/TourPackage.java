package com.company.model.domain.TourPackage;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tour_package")
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Column
    //@Enumerated(EnumType.STRING)
    private String type;

    @Column(name = "food_system")
    //@Enumerated(EnumType.STRING)
    private String foodSystem;
    @Column
    //@Enumerated(EnumType.STRING)
    private String transport;
    @Column
    private int days;

    //private int price;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFoodSystem() {
        return foodSystem;
    }

    public void setFoodSystem(String foodSystem) {
        this.foodSystem = foodSystem;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

   /* public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }*/

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
                statusHot == that.statusHot &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(foodSystem, that.foodSystem) &&
                Objects.equals(transport, that.transport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, foodSystem, transport, days, statusHot);
    }

    @Override
    public String toString() {
        return "TourPackage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", foodSystem='" + foodSystem + '\'' +
                ", transport='" + transport + '\'' +
                ", days=" + days +
                ", statusHot=" + statusHot +
                '}';
    }
}
