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

@Controller
public class RegistrationController {
    private UserService userService;

    @Autowired
    @Qualifier("userRegistrationValidator")
    private UserRegistrationValidator userRegistrationValidator;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showPageRegistration(Model model){
        model.addAttribute("registeredUser", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration/user", method = RequestMethod.POST)
    public String registerNewUser(
                    @ModelAttribute("registeredUser") User user,
                    BindingResult result,
                    @RequestParam String passwordRepeat) throws ServiceException {
        userRegistrationValidator.validate(user, passwordRepeat, result);
        if(result.hasErrors()) {
            return "registration";
        }
        userService.register(user);
        return "authorization";
    }
}
