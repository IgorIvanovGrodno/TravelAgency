package com.company.service.facade;

import com.company.controller.utils.ModelTourPackage;
import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.TypeTourPackage;
import com.company.model.domain.tourPackage.Transport;
import com.company.service.tourPackage.TourPackageService;
import com.company.service.tourPackage.foodSystem.FoodSystemService;
import com.company.service.tourPackage.transport.TransportService;
import com.company.service.tourPackage.typeTourPackage.TypeTourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacadeTourPackageImpl implements FacadeTourPackage {
    private FoodSystemService foodSystemService;
    private TransportService transportService;
    private TypeTourPackageService typeTourPackageService;
    private TourPackageService tourPackageService;

    @Autowired
    public FacadeTourPackageImpl(FoodSystemService foodSystemService, TransportService transportService, TypeTourPackageService typeTourPackageService, TourPackageService tourPackageService) {
        this.foodSystemService = foodSystemService;
        this.transportService = transportService;
        this.typeTourPackageService = typeTourPackageService;
        this.tourPackageService = tourPackageService;
    }

    @Override
    public List<TypeTourPackage> getTypesOfTours(){
        return typeTourPackageService.getAllTypes();
    }

    @Override
    public List<Transport> getTransportsOfTours() {
        return transportService.getAllTransports();
    }

    @Override
    public List<FoodSystem> getFoodSystemsOfTours() {
        return foodSystemService.getAllFoodSystems();
    }

    @Override
    public List<TourPackage> getTourPackages() {
        return tourPackageService.getTourPackages();
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) {
        return tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages);
    }

    @Override
    public TourPackage getTourPackage(Long id) {
        return tourPackageService.getTourPackage(id);
    }

    @Override
    public TourPackage createTourPackage(ModelTourPackage modelTourPackage) {
        TourPackage tourPackage = getTourPackageAccordingToSelectedParameters(modelTourPackage);
        return tourPackageService.createTourPackage(tourPackage);
    }

    @Override
    public void updateTourPackage(ModelTourPackage modelTourPackage) {
        TourPackage tourPackage = getTourPackageAccordingToSelectedParameters(modelTourPackage);
        tourPackageService.updateTourPackage(tourPackage);
    }

    @Override
    public void deleteTourPackage(Long id) {
        tourPackageService.deleteTourPackage(id);
    }

    private TourPackage getTourPackageAccordingToSelectedParameters(ModelTourPackage selectedParameters) {
        TourPackage tourPackage = new TourPackage();
        if(selectedParameters.getId()!=null) tourPackage.setId(selectedParameters.getId());
        tourPackage.setDays(Integer.parseInt(selectedParameters.getDay()));
        tourPackage.setName(selectedParameters.getDescription());
        tourPackage.setPrice(Integer.parseInt(selectedParameters.getPrice()));
        tourPackage.setStatusHot(selectedParameters.isStatusHot());
        tourPackage.setTransport(transportService.getTransportByName(selectedParameters.getValueOfTransport()));
        tourPackage.setType(typeTourPackageService.getTypeTourPackageByName(selectedParameters.getValueOfType()));
        tourPackage.setFoodSystem(foodSystemService.getFoodSystemByName(selectedParameters.getValueOfFoodSystem()));
        return tourPackage;
    }
}
