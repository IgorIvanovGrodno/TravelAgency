package com.company.model.dao.tourPackage.transport;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.tourPackage.Transport;
import com.company.model.domain.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


@Repository
@Transactional
public class HibernateTransportDAOImpl extends GenericHibernateDAO<Transport, Long> implements TransportDAO {

    @Autowired
    public HibernateTransportDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);

    }

    @Override
    public Transport findByName(String name) {
        TypedQuery<Transport> query = getSession().createQuery("from Transport where name=:name").setCacheable(true);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}
