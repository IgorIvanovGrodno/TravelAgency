package com.company.model.dao.tourPackage;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.dao.GenericDAO;
import com.company.model.domain.tourPackage.TourPackage;
import java.util.List;

public interface TourPackageDAO extends GenericDAO<TourPackage, Long> {

    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages);

    void deleteById(Long id);
}
