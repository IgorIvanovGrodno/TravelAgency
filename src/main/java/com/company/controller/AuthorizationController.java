package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorizationController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showRolePage() {

        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showRolPage() {

        return "admin";
    }

    @RequestMapping(value = "/admin")
    public String showPage() {

        return "admin";
    }
    @RequestMapping(value = "/user")
    public String showPageUser() {

        return "user";
    }
}
