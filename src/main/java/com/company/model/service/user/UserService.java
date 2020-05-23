package com.company.model.service.user;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.user.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * This class is interface for service which provides business logic's methods for working with users.
 *
 * @author Igor Ivanov
 */
public interface UserService
{
    /**
     * This method returns all users.
     *
     * @return optional list of all users.
     */
    Optional<List<User>> getAllUsers();

    /**
     * This method receives user and set him discount.
     *
     * @param user - user.
     * @throws ServiceException when input parameters are incorrect.
     */
    void setDiscount(User user) throws ServiceException;

    /**
     * This method receives user's login and returns user by login.
     *
     * @param loginUser - user's login.
     * @return optional of user by login.
     * @throws ServiceException
     */
    Optional<User> getUserByLogin(String loginUser) throws ServiceException;

    /**
     * This method receives user's login and checks exist user with this login.
     *
     * @param login - user's login.
     * @return true if user exists else false
     * @throws ServiceException when input parameters are incorrect.
     */
    boolean isExistUserWithLogin(String login) throws ServiceException;

    /**
     * This method receives user and registers him in system.
     *
     * @param user - user for registration.
     * @throws ServiceException when input parameters are incorrect.
     */
    void register(User user) throws ServiceException;

    /**
     * This method receives user's login and return orders of user with this login.
     *
     * @param login - user's login.
     * @return list orders of user with this login.
     * @throws ServiceException when input parameters are incorrect.
     */
    List<Order> getUsersOrders(String login) throws ServiceException;

    /**
     * This method receives principal and return his discount.
     *
     * @param principal - principal.
     * @return principal's discount.
     * @throws ServiceException when input parameters are incorrect.
     */
    int getDiscount(Principal principal) throws ServiceException;
}
