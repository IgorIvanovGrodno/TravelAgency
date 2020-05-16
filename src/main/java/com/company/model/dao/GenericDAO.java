package com.company.model.dao;

import java.io.Serializable;
import java.util.List;

/**
 * This interface is common repository interface, that provides CRUD operations with entities.
 *
 * @param <T>  - type of concrete repository.
 * @param <ID> - identifier of concrete repository.
 * @author Igor Ivanov
 */
public interface GenericDAO<T, ID extends Serializable>
{
    /**
     * This method finds entity by identifier.
     *
     * @param id - identifier.
     * @return - entity.
     */
    T findById(ID id);

    /**
     * This method return all stored in repository entity.
     *
     * @return list of stored entities.
     */
    List<T> findAll();

    /**
     * This method save entity in repository.
     *
     * @param entity - entity for saving.
     * @return saved entity.
     */
    T makePersistent(T entity);

    /**
     * This method delete entity in repository.
     *
     * @param entity - entity for deleting.
     */
    void makeTransient(T entity);
}
