package com.company.utils;

import java.util.Objects;

/**
 * This class is selected parameters of tour packages.
 *
 * @author Igor Ivanov
 */
public class ParametersSelectedForTourPackages
{
    /**
     * This field is identifier of food system.
     */
    private Long idOfFoodSystem;
    /**
     * This field is identifier of transport.
     */
    private Long idOfTransport;
    /**
     * This field is identifier of type.
     */
    private Long idOfType;
    /**
     * This field is status(hot/no hot) of tour package.
     */
    private boolean statusHot;
    /**
     * This field is minimal day.
     */
    private String minDay;
    /**
     * This field is maximal day.
     */
    private String maxDay;
    /**
     * This field is minimal price.
     */
    private String minPrice;
    /**
     * This field is maximal price.
     */
    private String maxPrice;

    /**
     * Default constructor.
     */
    public ParametersSelectedForTourPackages()
    {
    }

    /**
     * This method returns minimal day.
     *
     * @return minimal day.
     */
    public String getMinDay()
    {
        return minDay;
    }

    /**
     * This method sets minimal day.
     *
     * @param minDay - minimal day.
     */
    public void setMinDay(String minDay)
    {
        this.minDay = minDay;
    }

    /**
     * This method returns maximal day.
     *
     * @return maximal day.
     */
    public String getMaxDay()
    {
        return maxDay;
    }

    /**
     * This method sets maximal day.
     *
     * @param maxDay - maximal day.
     */
    public void setMaxDay(String maxDay)
    {
        this.maxDay = maxDay;
    }

    /**
     * This method returns minimal price.
     *
     * @return minimal price.
     */
    public String getMinPrice()
    {
        return minPrice;
    }

    /**
     * This method sets minimal price.
     *
     * @param minPrice - minimal price.
     */
    public void setMinPrice(String minPrice)
    {
        this.minPrice = minPrice;
    }

    /**
     * This method returns maximal price.
     *
     * @return maximal price.
     */
    public String getMaxPrice()
    {
        return maxPrice;
    }

    /**
     * This method sets maximal price.
     *
     * @param maxPrice - maximal price.
     */
    public void setMaxPrice(String maxPrice)
    {
        this.maxPrice = maxPrice;
    }

    /**
     * This method return status(hot/no hot) of tour package.
     *
     * @return status(hot / no hot) of tour package.
     */
    public boolean isStatusHot()
    {
        return statusHot;
    }

    /**
     * This method set status(hot/no hot) of tour package.
     *
     * @param statusHot - status(hot/no hot) of tour package.
     */
    public void setStatusHot(boolean statusHot)
    {
        this.statusHot = statusHot;
    }

    /**
     * This method return identifier of type tour package.
     *
     * @return identifier of type tour package.
     */
    public Long getIdOfType()
    {
        return idOfType;
    }

    /**
     * This method set identifier of type.
     *
     * @param idOfType - identifier of type.
     */
    public void setIdOfType(Long idOfType)
    {
        this.idOfType = idOfType;
    }

    /**
     * This method return identifier of food system.
     *
     * @return identifier of food system.
     */
    public Long getIdOfFoodSystem()
    {
        return idOfFoodSystem;
    }

    /**
     * This method set identifier of food system.
     *
     * @param idOfFoodSystem - identifier of food system.
     */
    public void setIdOfFoodSystem(Long idOfFoodSystem)
    {
        this.idOfFoodSystem = idOfFoodSystem;
    }

    /**
     * This method return identifier of transport.
     *
     * @return identifier of transport.
     */
    public Long getIdOfTransport()
    {
        return idOfTransport;
    }

    /**
     * This method set identifier of transport.
     *
     * @param idOfTransport - identifier of transport.
     */
    public void setIdOfTransport(Long idOfTransport)
    {
        this.idOfTransport = idOfTransport;
    }

    /**
     * This method used for compare objects of selected parameters of tour packages.
     *
     * @param o - another object.
     * @return true if objects is equals, else - false.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
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

    /**
     * This method return hash of selected parameter's object.
     *
     * @return hash of selected parameter's object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(idOfFoodSystem, idOfTransport, idOfType, statusHot, minDay, maxDay, minPrice, maxPrice);
    }

    /**
     * This method return string representation of selected parameters of tour packages.
     *
     * @return string representation of selected parameters of tour packages.
     */
    @Override
    public String toString()
    {
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
