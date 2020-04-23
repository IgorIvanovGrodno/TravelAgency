package com.company.model.DAO.order;

import com.company.model.DAO.utils.HibernateSessionUtil;
import com.company.model.domain.order.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateOrderDAOImpl implements OrderDAO {
    private HibernateSessionUtil hibernateSessionUtil;

    @Autowired
    public HibernateOrderDAOImpl(HibernateSessionUtil hibernateSessionUtil) {
        this.hibernateSessionUtil = hibernateSessionUtil;
    }

    @Override
    public long saveOrder(Order order) {
        return (long) hibernateSessionUtil.getSession().save(order);
    }

}
