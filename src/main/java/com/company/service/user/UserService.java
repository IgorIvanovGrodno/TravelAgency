package com.company.service.user;

import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    int setDiscount(User user);

    List<TourPackage> getUsersPaidTourPackages(String nameUser);

    User getUserByLogin(String loginUser);

    void updateUser(User user);

    void buyTourPackage(TourPackage tourPackage, String loginUser);
}
