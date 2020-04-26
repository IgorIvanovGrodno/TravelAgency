package com.company.model.service.tourPackage.typeTourPackage;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.typeTourPackage.TypeTourPackageDAO;
import com.company.model.domain.tourPackage.TypeTourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<List<TypeTourPackage>> getAllTypes() {
        return Optional.of(typeTourPackageDAO.findAll());
    }

    @Override
    public Optional<TypeTourPackage> getTypeTourPackageByName(String name) throws ServiceException {
        if(name==null) throw new ServiceException("Incorrect name of type tour of tour package");
        return Optional.of(typeTourPackageDAO.findByName(name));
    }
}
