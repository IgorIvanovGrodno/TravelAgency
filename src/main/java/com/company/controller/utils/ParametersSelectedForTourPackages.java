package com.company.controller.utils;

import javax.validation.constraints.Pattern;
import java.util.Objects;


public class ParametersSelectedForTourPackages {
    private String valueOfFoodSystem;
    private String valueOfTransport;
    private String valueOfType;
    private boolean statusHot;
    @Pattern(regexp = "\\d+", message = "Please, enter number")
    private String minDay;
    @Pattern(regexp = "\\d+", message = "Please, enter number")
    private String maxDay;
    @Pattern(regexp = "\\d+", message = "Please, enter number")
    private String minPrice;
    @Pattern(regexp = "\\d+", message = "Please, enter number")
    private String maxPrice;


    public ParametersSelectedForTourPackages() {
    }

    public String getMinDay() {
        return minDay;
    }

    public void setMinDay(String minDay) {
        this.minDay = minDay;
    }

    public String getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(String maxDay) {
        this.maxDay = maxDay;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
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
        ParametersSelectedForTourPackages that = (ParametersSelectedForTourPackages) o;
        return statusHot == that.statusHot &&
                Objects.equals(valueOfFoodSystem, that.valueOfFoodSystem) &&
                Objects.equals(valueOfTransport, that.valueOfTransport) &&
                Objects.equals(valueOfType, that.valueOfType) &&
                Objects.equals(minDay, that.minDay) &&
                Objects.equals(maxDay, that.maxDay) &&
                Objects.equals(minPrice, that.minPrice) &&
                Objects.equals(maxPrice, that.maxPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueOfFoodSystem, valueOfTransport, valueOfType, statusHot, minDay, maxDay, minPrice, maxPrice);
    }

    @Override
    public String toString() {
        return "ParametersSelectedForTourPackages{" +
                "valueOfFoodSystem='" + valueOfFoodSystem + '\'' +
                ", valueOfTransport='" + valueOfTransport + '\'' +
                ", valueOfType='" + valueOfType + '\'' +
                ", statusHot=" + statusHot +
                ", minDay='" + minDay + '\'' +
                ", maxDay='" + maxDay + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                '}';
    }
}
