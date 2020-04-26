package com.company.model.service.tourPackage.foodSystem;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.foodSystem.FoodSystemDAO;
import com.company.model.domain.tourPackage.FoodSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<FoodSystem> getAllFoodSystems() {
        Optional<List<FoodSystem>> optionalFoodSystem = Optional.of(foodSystemDAO.findAll());
        return optionalFoodSystem.orElse(new ArrayList<>());
    }

    @Override
    public FoodSystem getFoodSystemByName(String name) throws ServiceException {
        if (name==null) throw new ServiceException("Incorrect value of food system");
        Optional<FoodSystem> optionalFoodSystem = Optional.of(foodSystemDAO.findByName(name));
        return optionalFoodSystem.orElse(new FoodSystem());
    }
}
