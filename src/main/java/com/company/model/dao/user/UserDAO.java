package com.company.model.dao.user;

import com.company.model.dao.GenericDAO;
import com.company.model.domain.user.User;

/**
 * This interface is concrete repository interface, that provides operations for working with user's entities.
 *
 * @author Igor Ivanov
 */
public interface UserDAO extends GenericDAO<User, Long>
{
    /**
     * This method set discount to the user by identifier.
     *
     * @param id       - user's identifier.
     * @param discount - discount.
     * @return amount updated users.
     */
    int setDiscountById(Long id, int discount);

    /**
     * This method receive user's login and return user which have this login.
     *
     * @param loginUser - user's login.
     * @return user which have this login.
     */
    User getUserByLogin(String loginUser);
}
