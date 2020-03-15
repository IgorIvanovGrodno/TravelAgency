package com.company.model.DAO;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.TourPackage.TourPackage;
import java.util.List;

public interface TourPackageDAO {
    List<TourPackage> getAllTourPackages();
    TourPackage getTourPackageById(Long id);

    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages);
}
