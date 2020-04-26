package com.company.model.service.tourPackage.foodSystem;

import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.FoodSystem;

import java.util.List;

public interface FoodSystemService {
    List<FoodSystem> getAllFoodSystems();

    FoodSystem getFoodSystemByName(String name) throws ServiceException;
}
