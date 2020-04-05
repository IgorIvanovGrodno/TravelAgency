package com.company.model.DAO;

import com.company.model.domain.user.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    int setDiscountById(Long id, int discount);
}
