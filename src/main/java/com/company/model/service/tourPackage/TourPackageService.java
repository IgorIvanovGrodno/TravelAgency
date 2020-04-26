package com.company.model.service.tourPackage;

import com.company.exceptions.ServiceException;
import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.TourPackage;

import java.util.List;
import java.util.Optional;

public interface TourPackageService {

    List<TourPackage> getTourPackages();

    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) throws ServiceException;

    Optional<TourPackage> getTourPackage(Long id) throws ServiceException;

    Optional<TourPackage> createTourPackage(TourPackage tourPackage) throws ServiceException;

    void updateTourPackage(TourPackage tourPackage) throws ServiceException;

    void deleteTourPackage(Long id) throws ServiceException;

}
