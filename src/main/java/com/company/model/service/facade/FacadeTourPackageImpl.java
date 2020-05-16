package com.company.model.service.facade;

import com.company.exceptions.ServiceException;
import com.company.utils.ModelTourPackage;
import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.TypeTourPackage;
import com.company.model.domain.tourPackage.Transport;
import com.company.model.service.tourPackage.TourPackageService;
import com.company.model.service.tourPackage.foodSystem.FoodSystemService;
import com.company.model.service.tourPackage.transport.TransportService;
import com.company.model.service.tourPackage.typeTourPackage.TypeTourPackageService;
import com.company.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * This class is concrete implementation facade which provides business logic's methods for working with tour packages.
 *
 * @author Igor Ivanov
 */
@Component
public class FacadeTourPackageImpl implements FacadeTourPackage
{
    /**
     * This field is service which provides business logic's methods for working with food systems.
     */
    private FoodSystemService foodSystemService;
    /**
     * This field is service which provides business logic's methods for working with transports.
     */
    private TransportService transportService;
    /**
     * This field is service which provides business logic's methods for working with tour package's types.
     */
    private TypeTourPackageService typeTourPackageService;
    /**
     * This field is service which provides business logic's methods for working with tour packages.
     */
    private TourPackageService tourPackageService;
    /**
     * This field is service which provides business logic's methods for working with users.
     */
    private UserService userService;

    /**
     * Constructor which receive and assign food system's service, transport's service, tour package type's service,
     * tour package's service, user's service.
     *
     * @param foodSystemService      - food system's service.
     * @param transportService       - transport's service.
     * @param typeTourPackageService - tour package type's service.
     * @param tourPackageService     - tour package's service.
     * @param userService            - user's service.
     */
    @Autowired
    public FacadeTourPackageImpl(FoodSystemService foodSystemService, TransportService transportService,
                                 TypeTourPackageService typeTourPackageService,
                                 TourPackageService tourPackageService, UserService userService)
    {
        this.foodSystemService = foodSystemService;
        this.transportService = transportService;
        this.typeTourPackageService = typeTourPackageService;
        this.tourPackageService = tourPackageService;
        this.userService = userService;
    }

    /**
     * Method returns optional list of all types of tours.
     *
     * @return optional list of all types of tours.
     */
    @Override
    public Optional<List<TypeTourPackage>> getTypesOfTours()
    {
        return typeTourPackageService.getAllTypes();
    }

    /**
     * Method returns optional list of all transports of tours.
     *
     * @return optional list of all transports of tours.
     */
    @Override
    public Optional<List<Transport>> getTransportsOfTours()
    {
        return transportService.getAllTransports();
    }

    /**
     * Method returns optional list of all food systems of tours.
     *
     * @return optional list of all food systems of tours.
     */
    @Override
    public Optional<List<FoodSystem>> getFoodSystemsOfTours()
    {
        return foodSystemService.getAllFoodSystems();
    }

    /**
     * Method returns list of all tour packages.
     *
     * @return list of all tour packages.
     */
    @Override
    public List<TourPackage> getTourPackages()
    {
        return tourPackageService.getTourPackages();
    }

    /**
     * Method receives parameters for selecting tour packages and returns list of selected tour packages.
     * It throw ServiceException when used services throw this exception.
     *
     * @param parametersSelectedForTourPackages - parameters for selecting tour packages.
     * @return list of selected tour packages.
     * @throws ServiceException
     */
    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) throws ServiceException
    {
        return tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages);
    }

    /**
     * This method receives identifier of tour package and returns optional tour package with this identifier.
     * It throw ServiceException when used services throw this exception.
     *
     * @param id - identifier of tour package.
     * @return optional tour package with this identifier.
     * @throws ServiceException
     */
    @Override
    public Optional<TourPackage> getTourPackage(Long id) throws ServiceException
    {
        return tourPackageService.getTourPackage(id);
    }

    /**
     * This method receives model of tour package, creates new tour package and returns optional of new tour package.
     * It throw ServiceException when used services throw this exception.
     *
     * @param modelTourPackage - model of tour package.
     * @return optional of new tour package.
     * @throws ServiceException
     */
    @Override
    public Optional<TourPackage> createTourPackage(ModelTourPackage modelTourPackage) throws ServiceException
    {
        TourPackage tourPackage = getTourPackageAccordingToSelectedParameters(modelTourPackage);
        return tourPackageService.createTourPackage(tourPackage);
    }

    /**
     * This method receives model of tour package, updates tour package with same identifier.
     * It throw ServiceException when used services throw this exception.
     *
     * @param modelTourPackage - model of tour package.
     * @throws ServiceException
     */
    @Override
    public void updateTourPackage(ModelTourPackage modelTourPackage) throws ServiceException
    {
        TourPackage tourPackage = getTourPackageAccordingToSelectedParameters(modelTourPackage);
        tourPackageService.updateTourPackage(tourPackage);
    }

    /**
     * This method receives identifier of tour package, deletes tour package with this identifier.
     * It throw ServiceException when used services throw this exception.
     *
     * @param id - identifier of tour package.
     * @throws ServiceException
     */
    @Override
    public void deleteTourPackage(Long id) throws ServiceException
    {
        tourPackageService.deleteTourPackage(id);
    }

    /**
     * This method receives price of tour package and principal and return total count.
     * It throw ServiceException when used services throw this exception.
     *
     * @param price     - price of tour.
     * @param principal - principal.
     * @return total count.
     * @throws ServiceException
     */
    @Override
    public double getTotalPrice(int price, Principal principal) throws ServiceException
    {
        double totalPrice = price * (1 - userService.getDiscount(principal) * 0.01);
        return totalPrice;
    }

    /**
     * This private method receives parameters for selecting tour package and returns appropriate tour package.
     * It throw ServiceException when used services throw this exception.
     *
     * @param selectedParameters - parameters for selecting tour package.
     * @return tour package.
     * @throws ServiceException
     */
    private TourPackage getTourPackageAccordingToSelectedParameters(ModelTourPackage selectedParameters) throws ServiceException
    {
        TourPackage tourPackage = new TourPackage();
        if (selectedParameters.getId() != null)
        {
            tourPackage.setId(selectedParameters.getId());
        }
        tourPackage.setDays(Integer.parseInt(selectedParameters.getDay()));
        tourPackage.setName(selectedParameters.getDescription());
        tourPackage.setPrice(Integer.parseInt(selectedParameters.getPrice()));
        tourPackage.setStatusHot(selectedParameters.isStatusHot());
        tourPackage.setTransport(transportService.getTransportById(selectedParameters.getIdOfTransport()).orElseThrow(() -> new ServiceException("Not found transport")));
        tourPackage.setType(typeTourPackageService.getTypeTourPackageById(selectedParameters.getIdOfType()).orElseThrow(() -> new ServiceException("Not found type of tour package")));
        tourPackage.setFoodSystem(foodSystemService.getFoodSystemById(selectedParameters.getIdOfFoodSystem()).orElseThrow(() -> new ServiceException("Not found food system")));
        return tourPackage;
    }
}
