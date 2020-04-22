package com.company.model.DAO.tourPackage.typeTourPackage;

import com.company.model.domain.tourPackage.TourPackageType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HibernateTypeTourPackageDAOImpl implements TypeTourPackageDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateTypeTourPackageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<TourPackageType> getAllTypes() {
        TypedQuery<TourPackageType> query = currentSession().createQuery("from TourPackageType ").setCacheable(true);
        return query.getResultList();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
