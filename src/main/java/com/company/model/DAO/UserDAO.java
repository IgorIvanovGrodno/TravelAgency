package com.company.model.DAO;

import com.company.model.domain.user.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    int setDiscountById(Long id, int discount);

    User getUserByLogin(String loginUser);

    void updateUser(User user);

    void saveUser(User user);
}
