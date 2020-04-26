package com.company.model.service.tourPackage.typeTourPackage;

import com.company.model.dao.tourPackage.typeTourPackage.TypeTourPackageDAO;
import com.company.model.domain.tourPackage.TypeTourPackage;
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
    public List<TypeTourPackage> getAllTypes() {
        return typeTourPackageDAO.findAll();
    }

    @Override
    public TypeTourPackage getTypeTourPackageByName(String name) {
        return typeTourPackageDAO.findByName(name);
    }
}
