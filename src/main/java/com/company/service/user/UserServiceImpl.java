package com.company.service.user;

import com.company.model.DAO.UserDAO;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

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

    @Override
    public boolean isExistUserWithLogin(String login) {
        try{
            User user = userDAO.getUserByLogin(login);
            return user==null?false:true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    @Override
    public void register(User user) {
        user.getAuthorization().setRole("ROLE_USER");
        String hashedPassword = passwordEncoder.encode(user.getAuthorization().getPassword());
        user.getAuthorization().setPassword(hashedPassword);
        user.getAuthorization().setUser(user);
        userDAO.saveUser(user);
    }
}
