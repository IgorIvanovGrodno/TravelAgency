package com.company.service.user;

import com.company.exceptions.SetDiscountException;
import com.company.model.domain.order.Order;
import com.company.model.domain.user.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void setDiscount(User user) throws SetDiscountException;

    User getUserByLogin(String loginUser);

    boolean isExistUserWithLogin(String login);

    void register(User user);

    List<Order> getUsersOrders(String login);

    int getDiscount(Principal principal);
}
