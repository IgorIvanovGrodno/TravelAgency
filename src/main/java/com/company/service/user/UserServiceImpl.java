package com.company.service.user;

import com.company.model.DAO.UserDAO;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public int setDiscount(User user) {
        return userDAO.setDiscountById(user.getId(), user.getDiscount());
    }

    @Override
    public List<TourPackage> getUsersPaidTourPackages(String loginUser) {
        return userDAO.getUserByLogin(loginUser).getPaidTourPackages();
    }

    @Override
    public User getUserByLogin(String loginUser) {
        return userDAO.getUserByLogin(loginUser);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void buyTourPackage(TourPackage tourPackage, String loginUser) {
        User user = userDAO.getUserByLogin(loginUser);
        user.getPaidTourPackages().add(tourPackage);
        userDAO.updateUser(user);
    }
}
