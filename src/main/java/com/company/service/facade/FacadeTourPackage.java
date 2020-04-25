package com.company.service.facade;

import com.company.controller.utils.ModelTourPackage;
import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.TypeTourPackage;
import com.company.model.domain.tourPackage.Transport;

import java.util.List;

public interface FacadeTourPackage {
    List<TypeTourPackage> getTypesOfTours();

    List<Transport> getTransportsOfTours();

    List<FoodSystem> getFoodSystemsOfTours();

    List<TourPackage> getTourPackages();

    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages);

    TourPackage getTourPackage(Long id);

    TourPackage createTourPackage(ModelTourPackage modelTourPackage);

    void updateTourPackage(ModelTourPackage modelTourPackage);

    void deleteTourPackage(Long id);

}
