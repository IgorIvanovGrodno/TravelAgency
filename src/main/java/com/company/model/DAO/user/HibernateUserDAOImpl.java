package com.company.model.DAO.user;

import com.company.model.DAO.utils.HibernateSessionUtil;
import com.company.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class HibernateUserDAOImpl implements UserDAO {
    private HibernateSessionUtil hibernateSessionUtil;

    @Autowired
    public HibernateUserDAOImpl(HibernateSessionUtil hibernateSessionUtil) {
        this.hibernateSessionUtil = hibernateSessionUtil;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = hibernateSessionUtil.getSession().createQuery("from User ").setCacheable(true);
        return query.getResultList();
    }

    @Override
    public int setDiscountById(Long id, int discount) {
        TypedQuery<User> query = hibernateSessionUtil.getSession().createQuery("update User set discount=:discount where id=:id").setCacheable(true);
        query.setParameter("discount", discount);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    @Override
    public User getUserByLogin(String loginUser) {
        TypedQuery<User> query = hibernateSessionUtil.getSession().createQuery("from User where authorization.login=:login").setCacheable(true);
        query.setParameter("login", loginUser);
        return query.getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        hibernateSessionUtil.getSession().saveOrUpdate(user);
    }

    @Override
    public void saveUser(User user) {
        hibernateSessionUtil.getSession().save(user);
    }

}
