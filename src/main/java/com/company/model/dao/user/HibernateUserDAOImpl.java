package com.company.model.dao.user;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Repository
@Transactional
public class HibernateUserDAOImpl extends GenericHibernateDAO<User, Long> implements UserDAO {

    @Autowired
    public HibernateUserDAOImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public int setDiscountById(Long id, int discount) {
        TypedQuery<User> query = getSession().createQuery("update User set discount=:discount where id=:id").setCacheable(true);
        query.setParameter("discount", discount);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public User getUserByLogin(String loginUser) {
        TypedQuery<User> query = getSession().createQuery("from User where authorization.login=:login").setCacheable(true);
        query.setParameter("login", loginUser);
        return query.getSingleResult();
    }

}
