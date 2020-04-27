package com.company.model.dao.tourPackage.foodSystem;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.Transport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


@Repository
@Transactional
public class HibernateFoodSystemDAOImpl extends GenericHibernateDAO<FoodSystem, Long> implements FoodSystemDAO {

    @Autowired
    public HibernateFoodSystemDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
