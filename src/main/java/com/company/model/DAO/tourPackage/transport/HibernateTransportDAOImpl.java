package com.company.model.DAO.tourPackage.transport;

import com.company.model.domain.tourPackage.Transport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HibernateTransportDAOImpl implements TransportDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateTransportDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Transport> getAllTransports() {
        TypedQuery<Transport> query = currentSession().createQuery("from Transport ").setCacheable(true);
        return query.getResultList();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
