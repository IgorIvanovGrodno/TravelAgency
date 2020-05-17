package com.company.model.service.tourPackage.foodSystem;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.foodSystem.FoodSystemDAO;
import com.company.model.domain.tourPackage.FoodSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is implementation for service which provides business logic's methods for working with food systems.
 *
 * @author Igor Ivanov
 */
@Service
public class FoodSystemServiceImpl implements FoodSystemService
{
    /**
     * This field is food system's data access object.
     */
    private FoodSystemDAO foodSystemDAO;

    /**
     * Constructor which receives and assign food system's data access object.
     *
     * @param foodSystemDAO - food system's data access object.
     */
    @Autowired
    public FoodSystemServiceImpl(FoodSystemDAO foodSystemDAO)
    {
        this.foodSystemDAO = foodSystemDAO;
    }

    /**
     * This method returns all food systems.
     *
     * @return optional list of all food systems.
     */
    @Override
    public Optional<List<FoodSystem>> getAllFoodSystems()
    {
        return Optional.of(foodSystemDAO.findAll());
    }

    /**
     * This method receives identifier and returns food system by this identifier.
     * It throws ServiceException when input parameters are incorrect.
     *
     * @param idOfFoodSystem - identifier of food system.
     * @return optional of food system by this identifier.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public Optional<FoodSystem> getFoodSystemById(Long idOfFoodSystem) throws ServiceException
    {
        if (idOfFoodSystem == null)
        {
            throw new ServiceException("Incorrect value of food system");
        }
        return Optional.ofNullable(foodSystemDAO.findById(idOfFoodSystem));
    }
}
