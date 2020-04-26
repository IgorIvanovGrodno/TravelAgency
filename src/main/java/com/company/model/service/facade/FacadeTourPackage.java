package com.company.model.service.facade;

import com.company.exceptions.ServiceException;
import com.company.utils.ModelTourPackage;
import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.TypeTourPackage;
import com.company.model.domain.tourPackage.Transport;

import java.security.Principal;
import java.util.List;

public interface FacadeTourPackage {
    List<TypeTourPackage> getTypesOfTours();

    List<Transport> getTransportsOfTours();

    List<FoodSystem> getFoodSystemsOfTours();

    List<TourPackage> getTourPackages();

    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) throws ServiceException;

    TourPackage getTourPackage(Long id) throws ServiceException;

    TourPackage createTourPackage(ModelTourPackage modelTourPackage) throws ServiceException;

    void updateTourPackage(ModelTourPackage modelTourPackage) throws ServiceException;

    void deleteTourPackage(Long id) throws ServiceException;

    double getTotalPrice(int price, Principal principal) throws ServiceException;
}
