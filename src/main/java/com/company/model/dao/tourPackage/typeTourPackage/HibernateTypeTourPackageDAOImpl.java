package com.company.model.dao.tourPackage.typeTourPackage;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.tourPackage.TypeTourPackage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * This class is concrete repository, that provides implementation of operations for working with tour package's type entities.
 * It uses hibernate ORM for working with data base.
 *
 * @author Igor Ivanov
 */
@Repository
@Transactional
public class HibernateTypeTourPackageDAOImpl extends GenericHibernateDAO<TypeTourPackage, Long> implements TypeTourPackageDAO
{
    /**
     * Constructor which receive hibernate's session factory and pass it to super class constructor.
     *
     * @param sessionFactory - hibernate's session factory.
     */
    @Autowired
    public HibernateTypeTourPackageDAOImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }
}
