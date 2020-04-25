package com.company.service.user;

import com.company.exceptions.SetDiscountException;
import com.company.model.dao.user.UserDAO;
import com.company.model.domain.order.Order;
import com.company.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
        return userDAO.findAll();
    }

    @Override
    public void setDiscount(User user) throws SetDiscountException {
        //if(user==null||user.getId()==null) throw NullUserException();
        int result =userDAO.setDiscountById(user.getId(), user.getDiscount());
        if(result<=0) throw new SetDiscountException();

    }

    @Override
    public User getUserByLogin(String loginUser) {
        return userDAO.getUserByLogin(loginUser);
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
        user.getAuthorization().setActive(true);
        userDAO.makePersistent(user);
    }

    @Override
    public List<Order> getUsersOrders(String login) {
        User user = getUserByLogin(login);
        return user.getOrders().stream().sorted((order1, order2)->{return (int) (order2.getId()-order1.getId());}).collect(Collectors.toList());
    }

    @Override
    public int getDiscount(Principal principal) {
        User user = userDAO.getUserByLogin(principal.getName());
        return user.getDiscount();
    }
}
