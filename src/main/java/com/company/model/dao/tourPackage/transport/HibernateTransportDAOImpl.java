package com.company.model.dao.tourPackage.transport;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.tourPackage.Transport;
import com.company.model.domain.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * This class is concrete repository, that provides implementation of operations for working with transport's entities.
 * It uses hibernate ORM for working with data base.
 *
 * @author Igor Ivanov
 */
@Repository
@Transactional
public class HibernateTransportDAOImpl extends GenericHibernateDAO<Transport, Long> implements TransportDAO
{
    /**
     * Constructor which receive hibernate's session factory and pass it to super class constructor.
     *
     * @param sessionFactory - hibernate's session factory.
     */
    @Autowired
    public HibernateTransportDAOImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }
}
