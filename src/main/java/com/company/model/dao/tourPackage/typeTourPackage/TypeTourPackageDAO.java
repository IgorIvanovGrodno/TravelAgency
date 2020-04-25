package com.company.model.dao.tourPackage.typeTourPackage;

import com.company.model.dao.GenericDAO;
import com.company.model.domain.tourPackage.TypeTourPackage;

public interface TypeTourPackageDAO extends GenericDAO<TypeTourPackage, Long> {

    TypeTourPackage findByName(String name);
}
