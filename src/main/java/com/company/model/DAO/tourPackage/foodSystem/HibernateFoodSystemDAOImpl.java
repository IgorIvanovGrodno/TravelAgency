package com.company.model.DAO.tourPackage.foodSystem;

import com.company.model.DAO.utils.HibernateSessionUtil;
import com.company.model.domain.tourPackage.FoodSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HibernateFoodSystemDAOImpl implements FoodSystemDAO {
    private HibernateSessionUtil hibernateSessionUtil;

    @Autowired
    public HibernateFoodSystemDAOImpl(HibernateSessionUtil hibernateSessionUtil) {
        this.hibernateSessionUtil = hibernateSessionUtil;
    }

    @Override
    public List<FoodSystem> getAllFoodSystems() {
        TypedQuery<FoodSystem> query = hibernateSessionUtil.getSession().createQuery("from FoodSystem ").setCacheable(true);
        return query.getResultList();
    }

}
