package com.company.model.dao.tourPackage.typeTourPackage;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.tourPackage.TypeTourPackage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public class HibernateTypeTourPackageDAOImpl extends GenericHibernateDAO<TypeTourPackage, Long> implements TypeTourPackageDAO {

    @Autowired
    public HibernateTypeTourPackageDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);

    }

}
