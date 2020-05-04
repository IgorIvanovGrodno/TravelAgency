package com.company.model.service.tourPackage;

import com.company.exceptions.ServiceException;
import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.dao.tourPackage.TourPackageDAO;
import com.company.model.domain.tourPackage.TourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourPackageServiceImpl implements TourPackageService {
    private TourPackageDAO tourPackageDAO;

    @Autowired
    public TourPackageServiceImpl(TourPackageDAO tourPackageDAO) {
        this.tourPackageDAO = tourPackageDAO;
    }

    @Override
    public List<TourPackage> getTourPackages() {
        Optional<List<TourPackage>> optionalTourPackages = Optional.ofNullable(tourPackageDAO.findAll());
        return optionalTourPackages.orElse(new ArrayList<>());
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) throws ServiceException {
        if(parametersSelectedForTourPackages==null) throw new ServiceException("Incorrect data of selected parameters for tour package");
        Optional<List<TourPackage>> optionalTourPackages = Optional.ofNullable(tourPackageDAO.getSelectedTourPackages(parametersSelectedForTourPackages));
        return optionalTourPackages.orElse(new ArrayList<>());
    }

    @Override
    public Optional<TourPackage> getTourPackage(Long id) throws ServiceException {
        if(id==null) throw new ServiceException("Incorrect id of tour package");
        return Optional.ofNullable(tourPackageDAO.findById(id));
    }

    @Override
    public Optional<TourPackage> createTourPackage(TourPackage tourPackage) throws ServiceException {
        if(tourPackage==null) throw new ServiceException("Incorrect data of tour package");
        return Optional.ofNullable(tourPackageDAO.makePersistent(tourPackage));
    }

    @Override
    public void updateTourPackage(TourPackage tourPackage) throws ServiceException {
        if(tourPackage==null) throw new ServiceException("Incorrect data of tour package");
        tourPackageDAO.makePersistent(tourPackage);
    }

    @Override
    public void deleteTourPackage(Long id) throws ServiceException {
        if(id==null) throw new ServiceException("Incorrect id of tour package");
        tourPackageDAO.deleteById(id);
    }
}
