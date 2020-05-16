package com.company.model.dao.tourPackage.foodSystem;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.Transport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * This class is concrete repository, that provides implementation of operations for working with food system's entities.
 * It uses hibernate ORM for working with data base.
 *
 * @author Igor Ivanov
 */
@Repository
@Transactional
public class HibernateFoodSystemDAOImpl extends GenericHibernateDAO<FoodSystem, Long> implements FoodSystemDAO
{
    /**
     * Constructor which receive hibernate's session factory and pass it to super class constructor.
     *
     * @param sessionFactory - hibernate's session factory.
     */
    @Autowired
    public HibernateFoodSystemDAOImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

}
