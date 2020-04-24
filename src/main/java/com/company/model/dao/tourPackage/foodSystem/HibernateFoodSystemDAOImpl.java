package com.company.model.dao.tourPackage.foodSystem;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.tourPackage.FoodSystem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public class HibernateFoodSystemDAOImpl extends GenericHibernateDAO<FoodSystem, Long> implements FoodSystemDAO {

    @Autowired
    public HibernateFoodSystemDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


}
