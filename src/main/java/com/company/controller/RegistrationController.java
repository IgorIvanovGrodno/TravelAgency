package com.company.controller;

import com.company.exceptions.ServiceException;
import com.company.utils.validators.UserRegistrationValidator;
import com.company.model.domain.user.User;
import com.company.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * This class is controller for "registration" page.
 *
 * @author Igor Ivanov
 */
@Controller
public class RegistrationController
{
    /**
     * This field is user's service.
     */
    private UserService userService;

    /**
     * This field is registration's validator for user.
     */
    @Autowired
    @Qualifier("userRegistrationValidator")
    private UserRegistrationValidator userRegistrationValidator;

    /**
     * Constructor which receives and assigns user's service.
     *
     * @param userService - user's service.
     */
    @Autowired
    public RegistrationController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * This method handles request "/registration". It receives model, adds new object of User to model and return "registration" view.
     *
     * @param model - model.
     * @return "registration" view.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showPageRegistration(Model model)
    {
        model.addAttribute("registeredUser", new User());
        return "registration";
    }

    /**
     * This method handles request "/registration/user". It receives new user, binding result and repeated password. If this user is valid,
     * password and repeated password is same and input binding result doesn't have errors - registers user and returns "authorization" view.
     * Else - returns "registration" view.
     *
     * @param user           - new user.
     * @param result         - binding result.
     * @param passwordRepeat - repeated password.
     * @return if data is valid - "authorization", else - "registration" view.
     * @throws ServiceException when service throws ServiceException.
     */
    @RequestMapping(value = "/registration/user", method = RequestMethod.POST)
    public String registerNewUser(
            @ModelAttribute("registeredUser") User user,
            BindingResult result,
            @RequestParam String passwordRepeat) throws ServiceException
    {
        userRegistrationValidator.validate(user, passwordRepeat, result);
        if (result.hasErrors())
        {
            return "registration";
        }
        userService.register(user);
        return "authorization";
    }
}
