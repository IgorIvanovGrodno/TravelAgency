package com.company.model.service.tourPackage.foodSystem;

import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.FoodSystem;

import java.util.List;
import java.util.Optional;

/**
 * This class is interface for service which provides business logic's methods for working with food systems.
 *
 * @author Igor Ivanov
 */
public interface FoodSystemService
{
    /**
     * This method returns all food systems.
     *
     * @return optional list of all food systems.
     */
    Optional<List<FoodSystem>> getAllFoodSystems();

    /**
     * This method receives identifier and returns food system by this identifier.
     * It throws ServiceException when input parameters are incorrect.
     *
     * @param idOfFoodSystem - identifier of food system.
     * @return optional of food system by this identifier.
     * @throws ServiceException when input parameters are incorrect.
     */
    Optional<FoodSystem> getFoodSystemById(Long idOfFoodSystem) throws ServiceException;
}
