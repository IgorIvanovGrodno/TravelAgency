package com.company.model.DAO;

import com.company.model.domain.user.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HibernateUserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateUserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = currentSession().createQuery("from User ").setCacheable(true);
        return query.getResultList();
    }

    @Override
    public int setDiscountById(Long id, int discount) {
        TypedQuery<User> query = currentSession().createQuery("update User set discount=:discount where id=:id").setCacheable(true);
        query.setParameter("discount", discount);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public User getUserByLogin(String loginUser) {
        TypedQuery<User> query = currentSession().createQuery("from User where authorization.login=:login").setCacheable(true);
        query.setParameter("login", loginUser);
        return query.getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        currentSession().saveOrUpdate(user);
    }

    @Override
    public void saveUser(User user) {
        currentSession().save(user);
    }

    private Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
}
