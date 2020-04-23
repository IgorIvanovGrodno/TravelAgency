package com.company.model.DAO.order.statusOrder;

import com.company.model.DAO.utils.HibernateSessionUtil;
import com.company.model.domain.order.StatusOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateStatusOrderDAOImpl implements StatusOrderDAO {
    private HibernateSessionUtil hibernateSessionUtil;

    @Autowired
    public HibernateStatusOrderDAOImpl(HibernateSessionUtil hibernateSessionUtil) {
        this.hibernateSessionUtil = hibernateSessionUtil;
    }

    @Override
    public StatusOrder getStatusForNewOrder() {
        return hibernateSessionUtil.getSession().get(StatusOrder.class,1L);
    }

}
