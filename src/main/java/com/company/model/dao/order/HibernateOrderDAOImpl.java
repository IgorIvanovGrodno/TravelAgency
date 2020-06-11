package com.company.model.dao.order;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class is concrete repository, that provides implementation of operations for working with order's entities.
 * It uses hibernate ORM for working with data base.
 *
 * @author Igor Ivanov
 */
@Repository
@Transactional
public class HibernateOrderDAOImpl extends GenericHibernateDAO<Order, Long> implements OrderDAO
{
    /**
     * Constructor which receive hibernate's session factory and pass it to super class constructor.
     *
     * @param sessionFactory - hibernate's session factory.
     */
    @Autowired
    public HibernateOrderDAOImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    /**
     * This method returns new orders.
     * @return returns new orders.
     */
    @Override
    public List<Order> findNewOrders() {
        Query<Order> query = getSession().createQuery("from Order O where O.status.name='NEW'");
        return query.getResultList();
    }
}
