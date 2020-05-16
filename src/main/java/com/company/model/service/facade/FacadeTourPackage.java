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
import java.util.Optional;

/**
 * This class is interface for facade which provides business logic's methods for working with tour packages.
 *
 * @author Igor Ivanov
 */
public interface FacadeTourPackage
{
    /**
     * Method returns optional list of all types of tours.
     *
     * @return optional list of all types of tours.
     */
    Optional<List<TypeTourPackage>> getTypesOfTours();

    /**
     * Method returns optional list of all transports of tours.
     *
     * @return optional list of all transports of tours.
     */
    Optional<List<Transport>> getTransportsOfTours();

    /**
     * Method returns optional list of all food systems of tours.
     *
     * @return optional list of all food systems of tours.
     */
    Optional<List<FoodSystem>> getFoodSystemsOfTours();

    /**
     * Method returns list of all tour packages.
     *
     * @return list of all tour packages.
     */
    List<TourPackage> getTourPackages();

    /**
     * Method receives parameters for selecting tour packages and returns list of selected tour packages.
     * It throw ServiceException when used services throw this exception.
     *
     * @param parametersSelectedForTourPackages - parameters for selecting tour packages.
     * @return list of selected tour packages.
     * @throws ServiceException
     */
    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) throws ServiceException;

    /**
     * This method receives identifier of tour package and returns optional tour package with this identifier.
     * It throw ServiceException when used services throw this exception.
     *
     * @param id - identifier of tour package.
     * @return optional tour package with this identifier.
     * @throws ServiceException
     */
    Optional<TourPackage> getTourPackage(Long id) throws ServiceException;

    /**
     * This method receives model of tour package, creates new tour package and returns optional of new tour package.
     * It throw ServiceException when used services throw this exception.
     *
     * @param modelTourPackage - model of tour package.
     * @return optional of new tour package.
     * @throws ServiceException
     */
    Optional<TourPackage> createTourPackage(ModelTourPackage modelTourPackage) throws ServiceException;

    /**
     * This method receives model of tour package, updates tour package with same identifier.
     * It throw ServiceException when used services throw this exception.
     *
     * @param modelTourPackage - model of tour package.
     * @throws ServiceException
     */
    void updateTourPackage(ModelTourPackage modelTourPackage) throws ServiceException;

    /**
     * This method receives identifier of tour package, deletes tour package with this identifier.
     * It throw ServiceException when used services throw this exception.
     *
     * @param id - identifier of tour package.
     * @throws ServiceException
     */
    void deleteTourPackage(Long id) throws ServiceException;

    /**
     * This method receives price of tour package and principal and return total count.
     * It throw ServiceException when used services throw this exception.
     *
     * @param price     - price of tour.
     * @param principal - principal.
     * @return total count.
     * @throws ServiceException
     */
    double getTotalPrice(int price, Principal principal) throws ServiceException;
}
