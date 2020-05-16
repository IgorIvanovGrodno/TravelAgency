package com.company.model.dao.user;

import com.company.model.dao.GenericHibernateDAO;
import com.company.model.domain.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * This class is concrete repository, that provides implementation of operations for working with user's entities.
 * It uses hibernate ORM for working with data base.
 *
 * @author Igor Ivanov
 */
@Repository
@Transactional
public class HibernateUserDAOImpl extends GenericHibernateDAO<User, Long> implements UserDAO
{
    /**
     * Constructor which receive hibernate's session factory and pass it to super class constructor.
     *
     * @param sessionFactory - hibernate's session factory.
     */
    @Autowired
    public HibernateUserDAOImpl(SessionFactory sessionFactory)
    {
        super(sessionFactory);
    }

    /**
     * This method set discount to the user by identifier.
     *
     * @param id       - user's identifier.
     * @param discount - discount.
     * @return amount updated users.
     */
    @Override
    public int setDiscountById(Long id, int discount)
    {
        TypedQuery<User> query = getSession().createQuery("update User set discount=:discount where id=:id")
                .setCacheable(true);
        query.setParameter("discount", discount);
        query.setParameter("id", id);
        return query.executeUpdate();
    }

    /**
     * This method receive user's login and return user which have this login.
     *
     * @param loginUser - user's login.
     * @return user which have this login.
     */
    @Override
    public User getUserByLogin(String loginUser)
    {
        TypedQuery<User> query = getSession().createQuery("from User where authorization.login=:login")
                .setCacheable(true);
        query.setParameter("login", loginUser);
        return query.getSingleResult();
    }

}
