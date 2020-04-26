package com.company.service.tourPackage;

import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.TourPackage;

import java.util.List;

public interface TourPackageService {

    List<TourPackage> getTourPackages();

    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages);

    TourPackage getTourPackage(Long id);

    TourPackage createTourPackage(TourPackage tourPackage);

    void updateTourPackage(TourPackage tourPackage);

    void deleteTourPackage(Long id);

}
