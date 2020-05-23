package com.company.utils;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * This class is model of tour package.
 *
 * @author Igor Ivanov
 */
public class ModelTourPackage
{
    /**
     * This field is identifier of model tour package.
     */
    private Long id;

    /**
     * This field is description of model tour package.
     */
    @NotEmpty(message = "{validator.not.empty.description}")
    private String description;

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
     * This field is status(hot/no hot) of model tour package.
     */
    private boolean statusHot;

    /**
     * This field is amount of days.
     */
    @NotEmpty(message = "{validator.not.empty}")
    private String day;

    /**
     * This field is price of model tour package.
     */
    @NotEmpty(message = "{validator.not.empty}")
    private String price;

    /**
     * This method return identifier of model tour package.
     *
     * @return identifier of model tour package.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * This method set identifier of model tour package.
     *
     * @param id - identifier of model tour package.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * This method return description of model tour package.
     *
     * @return description of model tour package.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * This method set description of model tour package.
     *
     * @param description - description of model tour package.
     */
    public void setDescription(String description)
    {
        this.description = description;
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
     * This method return status(hot/no hot) of model tour package.
     *
     * @return status(hot / no hot) of model tour package.
     */
    public boolean isStatusHot()
    {
        return statusHot;
    }

    /**
     * This method set status(hot/no hot) of model tour package.
     *
     * @param statusHot - status(hot/no hot) of model tour package.
     */
    public void setStatusHot(boolean statusHot)
    {
        this.statusHot = statusHot;
    }

    /**
     * This method return amount of days.
     *
     * @return amount of days.
     */
    public String getDay()
    {
        return day;
    }

    /**
     * This method set amount of days.
     *
     * @param day - amount of days.
     */
    public void setDay(String day)
    {
        this.day = day;
    }

    /**
     * This method return price of model tour package.
     *
     * @return price of model tour package.
     */
    public String getPrice()
    {
        return price;
    }

    /**
     * This method set price of model tour package.
     *
     * @param price - price of model tour package.
     */
    public void setPrice(String price)
    {
        this.price = price;
    }

    /**
     * This method used for compare objects of tour package.
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
        ModelTourPackage that = (ModelTourPackage) o;
        return statusHot == that.statusHot &&
                Objects.equals(description, that.description) &&
                Objects.equals(idOfFoodSystem, that.idOfFoodSystem) &&
                Objects.equals(idOfTransport, that.idOfTransport) &&
                Objects.equals(idOfType, that.idOfType) &&
                Objects.equals(day, that.day) &&
                Objects.equals(price, that.price);
    }

    /**
     * This method return hash of model tour package's object.
     *
     * @return hash of model tour package's object.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(description, idOfFoodSystem, idOfTransport, idOfType, statusHot, day, price);
    }

    /**
     * This method return string representation of model tour package.
     *
     * @return string representation of model tour package.
     */
    @Override
    public String toString()
    {
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
