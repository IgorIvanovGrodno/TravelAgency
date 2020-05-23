package com.company.utils;


import javax.validation.constraints.Pattern;

/**
 * This class is model of order(number of card, total cost).
 *
 * @author Igor Ivanov
 */
public class OrderModel
{
    /**
     * This field is a number of payment card
     */
    @Pattern(regexp = "\\d{4}", message = "{validator.card.number}")
    private String numberCard;
    /**
     * This field is a total cost of model order.
     */
    private Double totalCost;

    /**
     * Default constructor.
     */
    public OrderModel()
    {
    }

    /**
     * This method return number of payment card.
     *
     * @return number of payment card.
     */
    public String getNumberCard()
    {
        return numberCard;
    }

    /**
     * This method set number of payment card.
     *
     * @param numberCard - number of payment card.
     */
    public void setNumberCard(String numberCard)
    {
        this.numberCard = numberCard;
    }

    /**
     * This method return total cost of model order.
     *
     * @return total cost of model order.
     */
    public Double getTotalCost()
    {
        return totalCost;
    }

    /**
     * This method set total cost of model order.
     *
     * @param totalCost -total cost of model order.
     */
    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }

    /**
     * This method return string representation of model order.
     *
     * @return string representation of model order.
     */
    @Override
    public String toString()
    {
        return "OrderModel{" +
                "numberCard='" + numberCard + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}
