package com.company.service.user;

import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    int setDiscount(User user);

    User getUserByLogin(String loginUser);

    boolean isExistUserWithLogin(String login);

    void register(User user);

    List<Order> getUsersOrders(String login);
}
