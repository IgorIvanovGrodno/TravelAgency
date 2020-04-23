package com.company.model.DAO.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionUtil {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateSessionUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession(){
        Session session = sessionFactory.getCurrentSession();
        if (session == null||!session.isOpen()) return sessionFactory.openSession();
        return session;
    }
}
