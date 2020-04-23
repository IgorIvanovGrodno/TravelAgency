package com.company.model.DAO.tourPackage.typeTourPackage;

import com.company.model.DAO.utils.HibernateSessionUtil;
import com.company.model.domain.tourPackage.TourPackageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HibernateTypeTourPackageDAOImpl implements TypeTourPackageDAO {
    private HibernateSessionUtil hibernateSessionUtil;

    @Autowired
    public HibernateTypeTourPackageDAOImpl(HibernateSessionUtil hibernateSessionUtil) {
        this.hibernateSessionUtil = hibernateSessionUtil;
    }

    @Override
    public List<TourPackageType> getAllTypes() {
        TypedQuery<TourPackageType> query = hibernateSessionUtil.getSession().createQuery("from TourPackageType ").setCacheable(true);
        return query.getResultList();
    }

}
