package com.company.model.service.tourPackage.typeTourPackage;

import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.TypeTourPackage;

import java.util.List;
import java.util.Optional;

public interface TypeTourPackageService {
    Optional<List<TypeTourPackage>> getAllTypes();

    Optional<TypeTourPackage> getTypeTourPackageByName(String name) throws ServiceException;
}
