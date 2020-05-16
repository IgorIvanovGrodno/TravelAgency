package com.company.model.dao.tourPackage;

import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.dao.GenericDAO;
import com.company.model.domain.tourPackage.TourPackage;

import java.util.List;

/**
 * This interface is concrete repository interface, that provides operations for working with tour package's entities.
 *
 * @author Igor Ivanov
 */
public interface TourPackageDAO extends GenericDAO<TourPackage, Long>
{
    /**
     * This method receive parameters for selecting tour packages and return selected tour packages.
     *
     * @param parametersSelectedForTourPackages - parameters for selecting tour packages.
     * @return selected tour packages.
     */
    List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages);

    /**
     * This method delete tour package by identifier.
     *
     * @param id - tour package's identifier.
     */
    void deleteById(Long id);
}
