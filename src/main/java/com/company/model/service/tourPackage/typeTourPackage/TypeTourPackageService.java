package com.company.model.service.tourPackage.typeTourPackage;

import com.company.exceptions.ServiceException;
import com.company.model.domain.tourPackage.TypeTourPackage;

import java.util.List;
import java.util.Optional;

/**
 * This class is interface for service which provides business logic's methods for working with tour package's types.
 *
 * @author Igor Ivanov
 */
public interface TypeTourPackageService
{
    /**
     * This method returns all tour package's types.
     *
     * @return optional list of all tour package's types.
     */
    Optional<List<TypeTourPackage>> getAllTypes();

    /**
     * This method receives identifier and returns tour package's type by this identifier.
     * It throws ServiceException when input parameters are incorrect.
     *
     * @param idOfType - identifier of tour package's type.
     * @return optional of tour package's type by this identifier.
     * @throws ServiceException when input parameters are incorrect.
     */
    Optional<TypeTourPackage> getTypeTourPackageById(Long idOfType) throws ServiceException;
}
