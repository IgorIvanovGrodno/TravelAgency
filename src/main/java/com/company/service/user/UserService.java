package com.company.service.user;

import com.company.model.domain.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    int setDiscount(User user);
}
