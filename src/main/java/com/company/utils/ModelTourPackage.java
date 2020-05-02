package com.company.utils;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class ModelTourPackage {
    private Long id;
    @NotEmpty(message = "{validator.not.empty.description}")
    private String description;
    private Long idOfFoodSystem;
    private Long idOfTransport;
    private Long idOfType;
    private boolean statusHot;
    @NotEmpty(message = "{validator.not.empty}")
    private String day;
    @NotEmpty(message = "{validator.not.empty}")
    private String price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdOfFoodSystem() {
        return idOfFoodSystem;
    }

    public void setIdOfFoodSystem(Long idOfFoodSystem) {
        this.idOfFoodSystem = idOfFoodSystem;
    }

    public Long getIdOfTransport() {
        return idOfTransport;
    }

    public void setIdOfTransport(Long idOfTransport) {
        this.idOfTransport = idOfTransport;
    }

    public Long getIdOfType() {
        return idOfType;
    }

    public void setIdOfType(Long idOfType) {
        this.idOfType = idOfType;
    }

    public boolean isStatusHot() {
        return statusHot;
    }

    public void setStatusHot(boolean statusHot) {
        this.statusHot = statusHot;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelTourPackage that = (ModelTourPackage) o;
        return statusHot == that.statusHot &&
                Objects.equals(description, that.description) &&
                Objects.equals(idOfFoodSystem, that.idOfFoodSystem) &&
                Objects.equals(idOfTransport, that.idOfTransport) &&
                Objects.equals(idOfType, that.idOfType) &&
                Objects.equals(day, that.day) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, idOfFoodSystem, idOfTransport, idOfType, statusHot, day, price);
    }

    @Override
    public String toString() {
        return "ModelTourPackage{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", valueOfFoodSystem='" + idOfFoodSystem + '\'' +
                ", valueOfTransport='" + idOfTransport + '\'' +
                ", valueOfType='" + idOfType + '\'' +
                ", statusHot=" + statusHot +
                ", day='" + day + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
