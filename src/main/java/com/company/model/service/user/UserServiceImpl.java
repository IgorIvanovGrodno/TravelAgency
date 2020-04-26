package com.company.model.service.user;

import com.company.exceptions.ServiceException;
import com.company.model.dao.user.UserDAO;
import com.company.model.domain.order.Order;
import com.company.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public Optional<List<User>> getAllUsers() {
        return Optional.of(userDAO.findAll());
    }

    @Override
    public void setDiscount(User user) throws ServiceException {
        if(user==null||user.getId()==null) throw new ServiceException("Incorrect input data of user");
        int result =userDAO.setDiscountById(user.getId(), user.getDiscount());
        if(result<=0) throw new ServiceException("Failed to set discount");
    }

    @Override
    public Optional<User> getUserByLogin(String loginUser) throws ServiceException {
        if(loginUser==null) throw new ServiceException("Incorrect input data of user's login");
        return Optional.of(userDAO.getUserByLogin(loginUser));
    }

    @Override
    public boolean isExistUserWithLogin(String login) throws ServiceException {
        if(login==null) throw new ServiceException("Incorrect input data of user's login");
        try{
            User user = userDAO.getUserByLogin(login);
            return user==null?false:true;
        }catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    @Override
    public void register(User user) throws ServiceException {
        if(user==null) throw new ServiceException("Incorrect input data of user");
        user.getAuthorization().setRole("ROLE_USER");
        String hashedPassword = passwordEncoder.encode(user.getAuthorization().getPassword());
        user.getAuthorization().setPassword(hashedPassword);
        user.getAuthorization().setUser(user);
        user.getAuthorization().setActive(true);
        userDAO.makePersistent(user);
    }

    @Override
    public List<Order> getUsersOrders(String login) throws ServiceException {
        Optional<User> optionalUser = getUserByLogin(login);
        if(optionalUser.isPresent()){
            return optionalUser.get().getOrders().stream().sorted((order1, order2)->{return (int) (order2.getId()-order1.getId());}).collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }

    @Override
    public int getDiscount(Principal principal) throws ServiceException {
        if(principal==null) throw new ServiceException("Incorrect data of authorization");
        Optional<User> optionalUser = Optional.of(userDAO.getUserByLogin(principal.getName()));
        return optionalUser.orElse(new User()).getDiscount();
    }
}
