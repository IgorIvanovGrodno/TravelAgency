package com.company.service;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.TourPackage.TourPackage;

import java.util.List;

public interface TourPackageService {

    List<TourPackage> getTourPackages();
    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages);
    TourPackage getTourPackage(Long id);
    Long createTourPackage(TourPackage tourPackage);
    void updateTourPackage(TourPackage tourPackage);

    void deleteTourPackage(TourPackage tourPackage);
}
