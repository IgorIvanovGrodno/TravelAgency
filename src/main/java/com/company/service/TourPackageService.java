package com.company.service;

import com.company.model.domain.TourPackage.TourPackage;

import java.util.List;

public interface TourPackageService {

    List<TourPackage> getTourPackages();
    TourPackage getTourPackage(Long id);

}
