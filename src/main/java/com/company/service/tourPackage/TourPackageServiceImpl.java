package com.company.service.tourPackage;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.dao.tourPackage.TourPackageDAO;
import com.company.model.domain.tourPackage.TourPackage;
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
        return sortListTourPackages(tourPackageDAO.findAll());
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) {
        return sortListTourPackages(tourPackageDAO.getSelectedTourPackages(parametersSelectedForTourPackages));
    }

    @Override
    public TourPackage getTourPackage(Long id) {
        return tourPackageDAO.findById(id);
    }

    @Override
    public TourPackage createTourPackage(TourPackage tourPackage) {
        return tourPackageDAO.makePersistent(tourPackage);
    }

    @Override
    public void updateTourPackage(TourPackage tourPackage) {
        tourPackageDAO.makePersistent(tourPackage);
    }

    @Override
    public void deleteTourPackage(Long id) {
        tourPackageDAO.deleteById(id);
    }

    private List<TourPackage> sortListTourPackages(List<TourPackage> listTourPackages) {
        return listTourPackages.stream().sorted().collect(Collectors.toList());
    }
}
