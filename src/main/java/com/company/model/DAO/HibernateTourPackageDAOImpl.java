package com.company.model.DAO;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.TourPackage.TourPackage;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class HibernateTourPackageDAOImpl implements TourPackageDAO {
    private SessionFactory sessionFactory;

    @Autowired
   public HibernateTourPackageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public HibernateTourPackageDAOImpl() {
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<TourPackage> getAllTourPackages() {
        TypedQuery<TourPackage> query = sessionFactory.getCurrentSession().createQuery("from TourPackage ");
        return query.getResultList();

    }

    @Override
    public TourPackage getTourPackageById(Long id) {
       // TypedQuery<TourPackage> query = sessionFactory.getCurrentSession().createQuery("select T from TourPackage T where T.id="+id);
        //return query.getSingleResult();
        return null;
    }

    @Override
    public List<TourPackage> getSelectedTourPackages(ParametersSelectedForTourPackages parametersSelectedForTourPackages) {
        return null;
    }
}
