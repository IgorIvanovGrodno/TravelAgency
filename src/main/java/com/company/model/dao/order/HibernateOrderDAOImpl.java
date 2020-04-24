package com.company.model.dao.order;


import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.order.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateOrderDAOImpl extends GenericHibernateDAO<Order, Long> implements OrderDAO {

    @Autowired
    public HibernateOrderDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
