package com.company.service.facadeService;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.TourPackageType;
import com.company.model.domain.tourPackage.Transport;
import com.company.service.tourPackage.TourPackageService;
import com.company.service.tourPackage.foodSystem.FoodSystemService;
import com.company.service.tourPackage.transport.TransportService;
import com.company.service.tourPackage.typeTourPackage.TypeTourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacadeTourPackageServiceImpl implements FacadeTourPackageService {
    private FoodSystemService foodSystemService;
    private TransportService transportService;
    private TypeTourPackageService typeTourPackageService;
    private TourPackageService tourPackageService;

    @Autowired
    public FacadeTourPackageServiceImpl(FoodSystemService foodSystemService, TransportService transportService, TypeTourPackageService typeTourPackageService, TourPackageService tourPackageService) {
        this.foodSystemService = foodSystemService;
        this.transportService = transportService;
        this.typeTourPackageService = typeTourPackageService;
        this.tourPackageService = tourPackageService;
    }

    @Override
    public List<TourPackageType> getTypesOfTours(){
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
    public Long createTourPackage(TourPackage tourPackage) {
        return tourPackageService.createTourPackage(tourPackage);
    }

    @Override
    public void updateTourPackage(TourPackage tourPackage) {
        tourPackageService.updateTourPackage(tourPackage);
    }

    @Override
    public void deleteTourPackage(TourPackage tourPackage) {
        tourPackageService.deleteTourPackage(tourPackage);
    }
}
