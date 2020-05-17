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

/**
 * This class is implementation for service which provides business logic's methods for working with tour packages.
 *
 * @author Igor Ivanov
 */
@Service
public class TourPackageServiceImpl implements TourPackageService
{
    /**
     * This field is tour package's data access object.
     */
    private TourPackageDAO tourPackageDAO;

    /**
     * Constructor which receives and assign tour package's data access object.
     *
     * @param tourPackageDAO
     */
    @Autowired
    public TourPackageServiceImpl(TourPackageDAO tourPackageDAO)
    {
        this.tourPackageDAO = tourPackageDAO;
    }

    /**
     * This method returns all tour packages.
     *
     * @return list of all tour packages.
     */
    @Override
    public List<TourPackage> getTourPackages()
    {
        Optional<List<TourPackage>> optionalTourPackages = Optional.ofNullable(tourPackageDAO.findAll());
        return optionalTourPackages.orElse(new ArrayList<>());
    }

    /**
     * This method receives parameters for selecting tour packages and returns selected tour packages.
     *
     * @param parametersSelectedForTourPackages - parameters for selecting tour packages.
     * @return - list of selected tour packages.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) throws ServiceException
    {
        if (parametersSelectedForTourPackages == null)
        {
            throw new ServiceException("Incorrect data of selected parameters for tour package");
        }
        Optional<List<TourPackage>> optionalTourPackages = Optional.ofNullable(tourPackageDAO.getSelectedTourPackages(parametersSelectedForTourPackages));
        return optionalTourPackages.orElse(new ArrayList<>());
    }

    /**
     * This method receives identifier and returns tour package by this identifier.
     *
     * @param id - identifier of tour package.
     * @return optional of tour package by this identifier.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public Optional<TourPackage> getTourPackage(Long id) throws ServiceException
    {
        if (id == null)
        {
            throw new ServiceException("Incorrect id of tour package");
        }
        return Optional.ofNullable(tourPackageDAO.findById(id));
    }

    /**
     * This method receives tour package for creating and adds it to repository.
     *
     * @param tourPackage - tour package for creating.
     * @return optional of created tour package.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public Optional<TourPackage> createTourPackage(TourPackage tourPackage) throws ServiceException
    {
        if (tourPackage == null)
        {
            throw new ServiceException("Incorrect data of tour package");
        }
        return Optional.ofNullable(tourPackageDAO.makePersistent(tourPackage));
    }

    /**
     * This method receives tour package for updating and update it in repository.
     *
     * @param tourPackage - tour package for updating.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public void updateTourPackage(TourPackage tourPackage) throws ServiceException
    {
        if (tourPackage == null)
        {
            throw new ServiceException("Incorrect data of tour package");
        }
        tourPackageDAO.makePersistent(tourPackage);
    }

    /**
     * This method receives identifier and delete tour package by this identifier.
     *
     * @param id - identifier of tour package for deleting.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public void deleteTourPackage(Long id) throws ServiceException
    {
        if (id == null)
        {
            throw new ServiceException("Incorrect id of tour package");
        }
        tourPackageDAO.deleteById(id);
    }
}
