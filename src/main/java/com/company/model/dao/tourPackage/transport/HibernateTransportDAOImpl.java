package com.company.model.dao.tourPackage.transport;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.tourPackage.Transport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;


@Repository
@Transactional
public class HibernateTransportDAOImpl extends GenericHibernateDAO<Transport, Long> implements TransportDAO {

    @Autowired
    public HibernateTransportDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);

    }
}
