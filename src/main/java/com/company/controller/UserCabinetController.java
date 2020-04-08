package com.company.controller;

import com.company.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserCabinetController {
    private UserService userService;

    @Autowired
    public UserCabinetController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user")
    public ModelAndView showPageUser(Principal principal) {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("paidTourPackages", userService.getUsersPaidTourPackages(principal.getName()));
        modelAndView.setViewName("userCabinet");
        return modelAndView;
    }
}
