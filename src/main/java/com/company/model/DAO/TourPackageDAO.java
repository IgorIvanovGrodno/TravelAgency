package com.company.model.DAO;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.TourPackage;
import java.util.List;

public interface TourPackageDAO {
    List<TourPackage> getAllTourPackages();

    TourPackage getTourPackageById(Long id);

    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages);

    Long createTourPackage(TourPackage tourPackage);

    void updateTourPackage(TourPackage tourPackage);

    void deleteTourPackage(TourPackage tourPackage);
}
