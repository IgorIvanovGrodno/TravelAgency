package com.company.model.dao.order.statusOrder;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.order.StatusOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateStatusOrderDAOImpl extends GenericHibernateDAO<StatusOrder, Long> implements StatusOrderDAO {

    @Autowired
    public HibernateStatusOrderDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
