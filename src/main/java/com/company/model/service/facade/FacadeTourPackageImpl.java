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

@Component
public class FacadeTourPackageImpl implements FacadeTourPackage {
    private FoodSystemService foodSystemService;
    private TransportService transportService;
    private TypeTourPackageService typeTourPackageService;
    private TourPackageService tourPackageService;
    private UserService userService;

    @Autowired
    public FacadeTourPackageImpl(FoodSystemService foodSystemService, TransportService transportService, TypeTourPackageService typeTourPackageService, TourPackageService tourPackageService, UserService userService) {
        this.foodSystemService = foodSystemService;
        this.transportService = transportService;
        this.typeTourPackageService = typeTourPackageService;
        this.tourPackageService = tourPackageService;
        this.userService = userService;
    }

    @Override
    public Optional<List<TypeTourPackage>> getTypesOfTours(){
        return typeTourPackageService.getAllTypes();
    }

    @Override
    public Optional<List<Transport>> getTransportsOfTours() {
        return transportService.getAllTransports();
    }

    @Override
    public Optional<List<FoodSystem>> getFoodSystemsOfTours() {
        return foodSystemService.getAllFoodSystems();
    }

    @Override
    public List<TourPackage> getTourPackages() {
        return tourPackageService.getTourPackages();
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) throws ServiceException {
        return tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages);
    }

    @Override
    public Optional<TourPackage> getTourPackage(Long id) throws ServiceException {
        return tourPackageService.getTourPackage(id);
    }

    @Override
    public Optional<TourPackage> createTourPackage(ModelTourPackage modelTourPackage) throws ServiceException {
        TourPackage tourPackage = getTourPackageAccordingToSelectedParameters(modelTourPackage);
        return tourPackageService.createTourPackage(tourPackage);
    }

    @Override
    public void updateTourPackage(ModelTourPackage modelTourPackage) throws ServiceException {
        TourPackage tourPackage = getTourPackageAccordingToSelectedParameters(modelTourPackage);
        tourPackageService.updateTourPackage(tourPackage);
    }

    @Override
    public void deleteTourPackage(Long id) throws ServiceException {
        tourPackageService.deleteTourPackage(id);
    }

    @Override
    public double getTotalPrice(int price, Principal principal) throws ServiceException {
        double totalPrice =price*(1-userService.getDiscount(principal)*0.01);
        return totalPrice;
    }

    private TourPackage getTourPackageAccordingToSelectedParameters(ModelTourPackage selectedParameters) throws ServiceException {
        TourPackage tourPackage = new TourPackage();
        if(selectedParameters.getId()!=null) tourPackage.setId(selectedParameters.getId());
        tourPackage.setDays(Integer.parseInt(selectedParameters.getDay()));
        tourPackage.setName(selectedParameters.getDescription());
        tourPackage.setPrice(Integer.parseInt(selectedParameters.getPrice()));
        tourPackage.setStatusHot(selectedParameters.isStatusHot());
        tourPackage.setTransport(transportService.getTransportById(selectedParameters.getIdOfTransport()).orElseThrow(()->new ServiceException("Not found transport")));
        tourPackage.setType(typeTourPackageService.getTypeTourPackageById(selectedParameters.getIdOfType()).orElseThrow(()->new ServiceException("Not found type of tour package")));
        tourPackage.setFoodSystem(foodSystemService.getFoodSystemById(selectedParameters.getIdOfFoodSystem()).orElseThrow(()->new ServiceException("Not found food system")));
        return tourPackage;
    }
}
