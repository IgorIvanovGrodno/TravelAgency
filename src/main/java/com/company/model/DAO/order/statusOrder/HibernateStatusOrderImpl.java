package com.company.model.DAO.order.statusOrder;

import com.company.model.domain.order.StatusOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateStatusOrderImpl implements StatusOrderDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateStatusOrderImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public StatusOrder getStatusForNewOrder() {
        return currentSession().get(StatusOrder.class,1L);
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
}
