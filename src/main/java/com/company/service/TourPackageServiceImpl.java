package com.company.service;

import com.company.model.DAO.TourPackageDAO;
import com.company.model.domain.TourPackage.TourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourPackageServiceImpl implements TourPackageService {
    TourPackageDAO tourPackageDAO;

    @Autowired
    public TourPackageServiceImpl(TourPackageDAO tourPackageDAO) {
        this.tourPackageDAO = tourPackageDAO;
    }

    @Override
    public List<TourPackage> getTourPackages() {
        return tourPackageDAO.getAllTourPackages();
    }

    @Override
    public TourPackage getTourPackage(Long id) {
        return tourPackageDAO.getTourPackageById(id);
    }
}
