package com.company.controller;

import java.util.Objects;

public class SelectForm {
    private String valueOfFoodSystem;
    private String valueOfTransport;
    private String valueOfType;
    private boolean statusHot;
    private int minDay;
    private int maxDay;
    private int minPrice;
    private int maxPrice;

    public int getMinDay() {
        return minDay;
    }

    public void setMinDay(int minDay) {
        this.minDay = minDay;
    }

    public int getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(int maxDay) {
        this.maxDay = maxDay;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean isStatusHot() {
        return statusHot;
    }

    public void setStatusHot(boolean statusHot) {
        this.statusHot = statusHot;
    }

    public String getValueOfType() {
        return valueOfType;
    }

    public void setValueOfType(String valueOfType) {
        this.valueOfType = valueOfType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectForm that = (SelectForm) o;
        return statusHot == that.statusHot &&
                minDay == that.minDay &&
                maxDay == that.maxDay &&
                minPrice == that.minPrice &&
                maxPrice == that.maxPrice &&
                Objects.equals(valueOfFoodSystem, that.valueOfFoodSystem) &&
                Objects.equals(valueOfTransport, that.valueOfTransport) &&
                Objects.equals(valueOfType, that.valueOfType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueOfFoodSystem, valueOfTransport, valueOfType, statusHot, minDay, maxDay, minPrice, maxPrice);
    }

    @Override
    public String toString() {
        return "SelectForm{" +
                "valueOfFoodSystem='" + valueOfFoodSystem + '\'' +
                ", valueOfTransport='" + valueOfTransport + '\'' +
                ", valueOfType='" + valueOfType + '\'' +
                ", statusHot=" + statusHot +
                ", minDay=" + minDay +
                ", maxDay=" + maxDay +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
