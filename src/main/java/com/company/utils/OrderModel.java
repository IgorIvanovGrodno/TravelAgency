package com.company.utils;


import javax.validation.constraints.Pattern;

public class OrderModel {
    @Pattern(regexp = "\\d{4}", message = "Please, enter correct number of card (4 digits)")
    private String numberCard;
    private Double totalCost;

    public OrderModel() {
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

    @Override
    public String toString() {
        return "OrderModel{" +
                "numberCard='" + numberCard + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}
