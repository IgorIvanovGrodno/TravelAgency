package model.domain.TourPackage;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tour_package")
public class TourPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private TourPackageType type;
    @Enumerated(EnumType.STRING)
    private FoodSystem foodSystem;
    private Transport transport;
    private int days;
    private int price;
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
}
