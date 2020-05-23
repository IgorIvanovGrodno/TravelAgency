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

/**
 * This class is implementation for service which provides business logic's methods for working with users.
 *
 * @author Igor Ivanov
 */
@Service
public class UserServiceImpl implements UserService
{
    /**
     * This field is user's data access object.
     */
    private UserDAO userDAO;

    /**
     * This field is encoder for passwords.
     */
    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    /**
     * Constructor which receives and assign tour user's data access object.
     *
     * @param userDAO - user's data access object.
     */
    @Autowired
    public UserServiceImpl(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    /**
     * This method returns all users.
     *
     * @return optional list of all users.
     */
    @Override
    public Optional<List<User>> getAllUsers()
    {
        return Optional.ofNullable(userDAO.findAll());
    }

    /**
     * This method receives user and set him discount.
     *
     * @param user - user.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public void setDiscount(User user) throws ServiceException
    {
        if (user == null || user.getId() == null)
        {
            throw new ServiceException("Incorrect input data of user");
        }
        int result = userDAO.setDiscountById(user.getId(), user.getDiscount());
        if (result <= 0) throw new ServiceException("Failed to set discount");
    }

    /**
     * This method receives user's login and returns user by login.
     *
     * @param loginUser - user's login.
     * @return optional of user by login.
     * @throws ServiceException
     */
    @Override
    public Optional<User> getUserByLogin(String loginUser) throws ServiceException
    {
        if (loginUser == null)
        {
            throw new ServiceException("Incorrect input data of user's login");
        }
        return Optional.ofNullable(userDAO.getUserByLogin(loginUser));
    }

    /**
     * This method receives user's login and checks exist user with this login.
     *
     * @param login - user's login.
     * @return true if user exists else false
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public boolean isExistUserWithLogin(String login) throws ServiceException
    {
        if (login == null)
        {
            throw new ServiceException("Incorrect input data of user's login");
        }
        try
        {
            User user = userDAO.getUserByLogin(login);
            return user == null ? false : true;
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }
    }

    /**
     * This method receives user and registers him in system.
     *
     * @param user - user for registration.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public void register(User user) throws ServiceException
    {
        if (user == null)
        {
            throw new ServiceException("Incorrect input data of user");
        }
        user.getAuthorization().setRole("ROLE_USER");
        String hashedPassword = passwordEncoder.encode(user.getAuthorization().getPassword());
        user.getAuthorization().setPassword(hashedPassword);
        user.getAuthorization().setUser(user);
        user.getAuthorization().setActive(true);
        userDAO.makePersistent(user);
    }

    /**
     * This method receives user's login and return orders of user with this login.
     *
     * @param login - user's login.
     * @return list orders of user with this login.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public List<Order> getUsersOrders(String login) throws ServiceException
    {
        Optional<User> optionalUser = getUserByLogin(login);
        if (optionalUser.isPresent())
        {
            return optionalUser.get().getOrders().stream().sorted((order1, order2) ->
            {
                return (int) (order2.getId() - order1.getId());
            }).collect(Collectors.toList());
        }
        else
        {
            return new ArrayList<>();
        }
    }

    /**
     * This method receives principal and return his discount.
     *
     * @param principal - principal.
     * @return principal's discount.
     * @throws ServiceException when input parameters are incorrect.
     */
    @Override
    public int getDiscount(Principal principal) throws ServiceException
    {
        if (principal == null)
        {
            throw new ServiceException("Incorrect data of authorization");
        }
        Optional<User> optionalUser = Optional.ofNullable(userDAO.getUserByLogin(principal.getName()));
        return optionalUser.orElse(new User()).getDiscount();
    }

    /**
     * This method returns encoder for passwords.
     *
     * @return encoder for passwords.
     */
    public PasswordEncoder getPasswordEncoder()
    {
        return passwordEncoder;
    }

    /**
     * This method receives and sets encoder for passwords.
     *
     * @param passwordEncoder - encoder for passwords.
     */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }
}
