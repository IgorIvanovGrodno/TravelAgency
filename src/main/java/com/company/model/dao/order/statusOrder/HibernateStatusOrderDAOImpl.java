package com.company.model.dao.order.statusOrder;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.order.StatusOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * This class is concrete repository, that provides implementation of operations for working with order's status entities.
 * It uses hibernate ORM for working with data base.
 *
 * @author Igor Ivanov
 */
@Repository
@Transactional
public class HibernateStatusOrderDAOImpl extends GenericHibernateDAO<StatusOrder, Long> implements StatusOrderDAO
{
    /**
     * Constructor which receive hibernate's session factory and pass it to super class constructor.
     *
     * @param sessionFactory - hibernate's session factory.
     */
    @Autowired
    public HibernateStatusOrderDAOImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }
}
