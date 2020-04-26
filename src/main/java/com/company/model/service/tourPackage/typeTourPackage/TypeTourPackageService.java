package com.company.model.service.tourPackage.typeTourPackage;

import com.company.model.domain.tourPackage.TypeTourPackage;

import java.util.List;

public interface TypeTourPackageService {
    List<TypeTourPackage> getAllTypes();

    TypeTourPackage getTypeTourPackageByName(String name);
}
