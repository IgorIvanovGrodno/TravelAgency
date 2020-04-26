package com.company.model.service.tourPackage.typeTourPackage;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.typeTourPackage.TypeTourPackageDAO;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.TypeTourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeTourPackageServiceImpl implements TypeTourPackageService {
    private TypeTourPackageDAO typeTourPackageDAO;

    @Autowired
    public TypeTourPackageServiceImpl(TypeTourPackageDAO typeTourPackageDAO) {
        this.typeTourPackageDAO = typeTourPackageDAO;
    }

    @Override
    public List<TypeTourPackage> getAllTypes() {
        Optional<List<TypeTourPackage>> optionalTypeTourPackages = Optional.of(typeTourPackageDAO.findAll());
        return optionalTypeTourPackages.orElse(new ArrayList<>());
    }

    @Override
    public TypeTourPackage getTypeTourPackageByName(String name) throws ServiceException {
        if(name==null) throw new ServiceException("Incorrect name of type tour of tour package");
        Optional<TypeTourPackage> optionalTypeTourPackage = Optional.of(typeTourPackageDAO.findByName(name));
        return optionalTypeTourPackage.orElse(new TypeTourPackage());
    }
}
