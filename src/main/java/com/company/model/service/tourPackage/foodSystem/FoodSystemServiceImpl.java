package com.company.model.service.tourPackage.foodSystem;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.foodSystem.FoodSystemDAO;
import com.company.model.domain.tourPackage.FoodSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodSystemServiceImpl implements FoodSystemService {
    private FoodSystemDAO foodSystemDAO;

    @Autowired
    public FoodSystemServiceImpl(FoodSystemDAO foodSystemDAO) {
        this.foodSystemDAO = foodSystemDAO;
    }

    @Override
    public Optional<List<FoodSystem>> getAllFoodSystems() {
       return Optional.of(foodSystemDAO.findAll());
    }

    @Override
    public Optional<FoodSystem> getFoodSystemById(Long idOfFoodSystem) throws ServiceException {
        if (idOfFoodSystem==null) throw new ServiceException("Incorrect value of food system");
        return Optional.ofNullable(foodSystemDAO.findById(idOfFoodSystem));
    }
}
