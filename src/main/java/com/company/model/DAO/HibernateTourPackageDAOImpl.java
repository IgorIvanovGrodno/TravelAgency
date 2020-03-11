package com.company.model.DAO;

import com.company.model.domain.TourPackage.TourPackage;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HibernateTourPackageDAOImpl implements TourPackageDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateTourPackageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<TourPackage> getAllTourPackages() {
        TypedQuery<TourPackage> query = sessionFactory.getCurrentSession().createQuery("from TourPackage ");
        return query.getResultList();
    }
}
