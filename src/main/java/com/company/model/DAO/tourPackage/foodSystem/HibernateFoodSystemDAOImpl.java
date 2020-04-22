package com.company.model.DAO.tourPackage.foodSystem;

import com.company.model.domain.tourPackage.FoodSystem;
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
public class HibernateFoodSystemDAOImpl implements FoodSystemDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateFoodSystemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<FoodSystem> getAllFoodSystems() {
        TypedQuery<FoodSystem> query = currentSession().createQuery("from FoodSystem ").setCacheable(true);
        return query.getResultList();
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
