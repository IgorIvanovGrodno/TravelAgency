package com.company.model.service.tourPackage.typeTourPackage;

import com.company.exceptions.ServiceException;
import com.company.model.dao.tourPackage.typeTourPackage.TypeTourPackageDAO;
import com.company.model.domain.tourPackage.TypeTourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class is implementation for service which provides business logic's methods for working with tour package's types.
 *
 * @author Igor Ivanov
 */
@Service
public class TypeTourPackageServiceImpl implements TypeTourPackageService
{
    /**
     * This field is tour package type's data access object.
     */
    private TypeTourPackageDAO typeTourPackageDAO;

    /**
     * Constructor which receives and assign tour package type's data access object.
     *
     * @param typeTourPackageDAO - tour package type's data access object.
     */
    @Autowired
    public TypeTourPackageServiceImpl(TypeTourPackageDAO typeTourPackageDAO)
    {
        this.typeTourPackageDAO = typeTourPackageDAO;
    }

    /**
     * This method returns all tour package's types.
     *
     * @return optional list of all tour package's types.
     */
    @Override
    public Optional<List<TypeTourPackage>> getAllTypes()
    {
        return Optional.ofNullable(typeTourPackageDAO.findAll());
    }

    /**
     * This method receives identifier and returns tour package's type by this identifier.
     * It throws ServiceException when input parameters are incorrect.
     *
     * @param idOfType - identifier of tour package's type.
     * @return optional of tour package's type by this identifier.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public Optional<TypeTourPackage> getTypeTourPackageById(Long idOfType) throws ServiceException
    {
        if (idOfType == null)
        {
            throw new ServiceException("Incorrect name of type tour of tour package");
        }
        return Optional.ofNullable(typeTourPackageDAO.findById(idOfType));
    }
}
