package com.company.model.service.tourPackage.foodSystem;

import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.FoodSystem;

import java.util.List;
import java.util.Optional;

public interface FoodSystemService {
    Optional<List<FoodSystem>> getAllFoodSystems();

    Optional<FoodSystem> getFoodSystemById(Long idOfFoodSystem) throws ServiceException;
}
