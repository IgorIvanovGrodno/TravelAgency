package com.company.service.tourPackage;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.DAO.tourPackage.TourPackageDAO;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.TourPackageType;
import com.company.model.domain.tourPackage.Transport;
import com.company.service.tourPackage.foodSystem.FoodSystemService;
import com.company.service.tourPackage.transport.TransportService;
import com.company.service.tourPackage.typeTourPackage.TypeTourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourPackageServiceImpl implements TourPackageService {
    private TourPackageDAO tourPackageDAO;

    @Autowired
    public TourPackageServiceImpl(TourPackageDAO tourPackageDAO) {
        this.tourPackageDAO = tourPackageDAO;
    }

    @Override
    public List<TourPackage> getTourPackages() {
        return sortListTourPackages(tourPackageDAO.getAllTourPackages());
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) {
        return sortListTourPackages(tourPackageDAO.getSelectedTourPackages(parametersSelectedForTourPackages));
    }

    @Override
    public TourPackage getTourPackage(Long id) {
        return tourPackageDAO.getTourPackageById(id);
    }

    @Override
    public Long createTourPackage(TourPackage tourPackage) {
        return tourPackageDAO.createTourPackage(tourPackage);
    }

    @Override
    public void updateTourPackage(TourPackage tourPackage) {
        tourPackageDAO.updateTourPackage(tourPackage);
    }

    @Override
    public void deleteTourPackage(TourPackage tourPackage) {
        tourPackageDAO.deleteTourPackage(tourPackage);
    }

    private List<TourPackage> sortListTourPackages(List<TourPackage> listTourPackages) {
        return listTourPackages.stream().sorted().collect(Collectors.toList());
    }


}
