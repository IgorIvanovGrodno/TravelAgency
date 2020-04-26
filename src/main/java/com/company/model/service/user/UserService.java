package com.company.model.service.user;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.user.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void setDiscount(User user) throws ServiceException;

    User getUserByLogin(String loginUser) throws ServiceException;

    boolean isExistUserWithLogin(String login) throws ServiceException;

    void register(User user) throws ServiceException;

    List<Order> getUsersOrders(String login) throws ServiceException;

    int getDiscount(Principal principal) throws ServiceException;
}