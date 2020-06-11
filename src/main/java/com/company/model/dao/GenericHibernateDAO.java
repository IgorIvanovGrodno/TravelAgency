package com.company.model.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * This class is common abstract repository class, that provides implementation CRUD operations with entities.
 * It uses hibernate ORM for working with data base.
 *
 * @param <T>  - type of concrete repository.
 * @param <ID> - identifier of concrete repository.
 * @author Igor Ivanov
 */
@Transactional
public abstract class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID>
{
    /**
     * This field is class of concrete repository.
     */
    private Class<T> persistentClass;

    /**
     * This field is hibernate's session factory.
     */
    private SessionFactory sessionFactory;

    /**
     * Constructor which receive and assign hibernate's session factory.
     *
     * @param sessionFactory hibernate's session factory.
     */
    public GenericHibernateDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * This protected method return either current hibernate's session or open new session.
     *
     * @return hibernate's session.
     */
    protected Session getSession()
    {
        Session session;
        try
        {
            session = sessionFactory.getCurrentSession();
        }
        catch (HibernateException e)
        {
            session = sessionFactory.openSession();
        }
        return session;
    }

    /**
     * This method return class of concrete repository.
     *
     * @return class of concrete repository.
     */
    public Class<T> getPersistentClass()
    {
        return persistentClass;
    }

    /**
     * This method finds entity by identifier.
     *
     * @param id - identifier.
     * @return - entity.
     */

    public T findById(ID id)
    {
        T entity = (T) getSession().get(getPersistentClass(), id);
        return entity;
    }

    /**
     * This method return all stored in repository entity.
     *
     * @return list of stored entities.
     */

    public List<T> findAll()
    {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(persistentClass);
        Root<T> root = criteriaQuery.from(persistentClass);
        criteriaQuery.select(root);
        Query<T> query = getSession().createQuery(criteriaQuery);
        List<T> results = query.getResultList();
        return results;
    }

    /**
     * This method save entity in repository.
     *
     * @param entity - entity for saving.
     * @return saved entity.
     */
    public T makePersistent(T entity)
    {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    /**
     * This method delete entity in repository.
     *
     * @param entity - entity for deleting.
     */
    public void makeTransient(T entity)
    {
        getSession().delete(entity);
    }
}
