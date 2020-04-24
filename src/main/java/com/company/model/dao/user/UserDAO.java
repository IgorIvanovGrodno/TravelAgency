package com.company.model.dao.user;

import com.company.model.dao.GenericDAO;
import com.company.model.domain.user.User;

public interface UserDAO extends GenericDAO<User, Long> {

    int setDiscountById(Long id, int discount);

    User getUserByLogin(String loginUser);
}
