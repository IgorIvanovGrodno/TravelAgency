package com.company.service.tourPackage.typeTourPackage;

import com.company.model.DAO.tourPackage.typeTourPackage.TypeTourPackageDAO;
import com.company.model.domain.tourPackage.TourPackageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeTourPackageServiceImpl implements TypeTourPackageService {
    private TypeTourPackageDAO typeTourPackageDAO;

    @Autowired
    public TypeTourPackageServiceImpl(TypeTourPackageDAO typeTourPackageDAO) {
        this.typeTourPackageDAO = typeTourPackageDAO;
    }

    @Override
    public List<TourPackageType> getAllTypes() {
        return typeTourPackageDAO.getAllTypes();
    }
}
