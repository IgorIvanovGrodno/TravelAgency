package com.company.service.tourPackage.foodSystem;

import com.company.model.dao.tourPackage.foodSystem.FoodSystemDAO;
import com.company.model.domain.tourPackage.FoodSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodSystemServiceImpl implements FoodSystemService {
    private FoodSystemDAO foodSystemDAO;

    @Autowired
    public FoodSystemServiceImpl(FoodSystemDAO foodSystemDAO) {
        this.foodSystemDAO = foodSystemDAO;
    }

    @Override
    public List<FoodSystem> getAllFoodSystems() {
        return foodSystemDAO.findAll();
    }

    @Override
    public FoodSystem getFoodSystemByName(String name) {
        return foodSystemDAO.findByName(name);
    }
}
