package com.company.controller.utils;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class ModelTourPackage {
    private Long id;
    @NotEmpty(message = "Description shouldn't be empty!")
    private String description;
    private String valueOfFoodSystem;
    private String valueOfTransport;
    private String valueOfType;
    private boolean statusHot;
    @NotEmpty(message = "Please, enter count of days!")
    private String day;
    @NotEmpty(message = "Please, enter price!")
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

    public String getValueOfFoodSystem() {
        return valueOfFoodSystem;
    }

    public void setValueOfFoodSystem(String valueOfFoodSystem) {
        this.valueOfFoodSystem = valueOfFoodSystem;
    }

    public String getValueOfTransport() {
        return valueOfTransport;
    }

    public void setValueOfTransport(String valueOfTransport) {
        this.valueOfTransport = valueOfTransport;
    }

    public String getValueOfType() {
        return valueOfType;
    }

    public void setValueOfType(String valueOfType) {
        this.valueOfType = valueOfType;
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
                Objects.equals(valueOfFoodSystem, that.valueOfFoodSystem) &&
                Objects.equals(valueOfTransport, that.valueOfTransport) &&
                Objects.equals(valueOfType, that.valueOfType) &&
                Objects.equals(day, that.day) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, valueOfFoodSystem, valueOfTransport, valueOfType, statusHot, day, price);
    }

    @Override
    public String toString() {
        return "ModelTourPackage{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", valueOfFoodSystem='" + valueOfFoodSystem + '\'' +
                ", valueOfTransport='" + valueOfTransport + '\'' +
                ", valueOfType='" + valueOfType + '\'' +
                ", statusHot=" + statusHot +
                ", day='" + day + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
