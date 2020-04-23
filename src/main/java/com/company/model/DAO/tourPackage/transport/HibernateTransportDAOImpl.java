package com.company.model.DAO.tourPackage.transport;

import com.company.model.DAO.utils.HibernateSessionUtil;
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
    private HibernateSessionUtil hibernateSessionUtil;

    @Autowired
    public HibernateTransportDAOImpl(HibernateSessionUtil hibernateSessionUtil) {
        this.hibernateSessionUtil = hibernateSessionUtil;
    }

    @Override
    public List<Transport> getAllTransports() {
        TypedQuery<Transport> query = hibernateSessionUtil.getSession().createQuery("from Transport ").setCacheable(true);
        return query.getResultList();
    }

}
