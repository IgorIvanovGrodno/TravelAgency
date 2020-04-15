package com.company.model.DAO.order;

import com.company.model.domain.order.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateOrderDAOImpl implements OrderDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateOrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long saveOrder(Order order) {
        return (long) currentSession().save(order);
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
}
