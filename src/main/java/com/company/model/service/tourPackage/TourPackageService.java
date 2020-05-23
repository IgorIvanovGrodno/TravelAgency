package com.company.model.service.tourPackage;

import com.company.exceptions.ServiceException;
import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.TourPackage;

import java.util.List;
import java.util.Optional;

/**
 * This class is interface for service which provides business logic's methods for working with tour packages.
 *
 * @author Igor Ivanov
 */
public interface TourPackageService
{
    /**
     * This method returns all tour packages.
     *
     * @return list of all tour packages.
     */
    List<TourPackage> getTourPackages();

    /**
     * This method receives parameters for selecting tour packages and returns selected tour packages.
     *
     * @param parametersSelectedForTourPackages - parameters for selecting tour packages.
     * @return - list of selected tour packages.
     * @throws ServiceException when input parameters are incorrect.
     */
    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) throws ServiceException;

    /**
     * This method receives identifier and returns tour package by this identifier.
     *
     * @param id - identifier of tour package.
     * @return optional of tour package by this identifier.
     * @throws ServiceException when input parameters are incorrect.
     */
    Optional<TourPackage> getTourPackage(Long id) throws ServiceException;

    /**
     * This method receives tour package for creating and adds it to repository.
     *
     * @param tourPackage - tour package for creating.
     * @return optional of created tour package.
     * @throws ServiceException when input parameters are incorrect.
     */
    Optional<TourPackage> createTourPackage(TourPackage tourPackage) throws ServiceException;

    /**
     * This method receives tour package for updating and update it in repository.
     *
     * @param tourPackage - tour package for updating.
     * @throws ServiceException when input parameters are incorrect.
     */
    void updateTourPackage(TourPackage tourPackage) throws ServiceException;

    /**
     * This method receives identifier and delete tour package by this identifier.
     *
     * @param id - identifier of tour package for deleting.
     * @throws ServiceException when input parameters are incorrect.
     */
    void deleteTourPackage(Long id) throws ServiceException;
}
