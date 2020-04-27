package com.company.utils;

import java.util.Objects;


public class ParametersSelectedForTourPackages {
    private Long idOfFoodSystem;
    private Long idOfTransport;
    private Long idOfType;
    private boolean statusHot;
    private String minDay;
    private String maxDay;
    private String minPrice;
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

    public Long getIdOfType() {
        return idOfType;
    }

    public void setIdOfType(Long idOfType) {
        this.idOfType = idOfType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParametersSelectedForTourPackages that = (ParametersSelectedForTourPackages) o;
        return statusHot == that.statusHot &&
                Objects.equals(idOfFoodSystem, that.idOfFoodSystem) &&
                Objects.equals(idOfTransport, that.idOfTransport) &&
                Objects.equals(idOfType, that.idOfType) &&
                Objects.equals(minDay, that.minDay) &&
                Objects.equals(maxDay, that.maxDay) &&
                Objects.equals(minPrice, that.minPrice) &&
                Objects.equals(maxPrice, that.maxPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOfFoodSystem, idOfTransport, idOfType, statusHot, minDay, maxDay, minPrice, maxPrice);
    }

    @Override
    public String toString() {
        return "ParametersSelectedForTourPackages{" +
                "valueOfFoodSystem='" + idOfFoodSystem + '\'' +
                ", valueOfTransport='" + idOfTransport + '\'' +
                ", valueOfType='" + idOfType + '\'' +
                ", statusHot=" + statusHot +
                ", minDay='" + minDay + '\'' +
                ", maxDay='" + maxDay + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                '}';
    }
}
