package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorizationController {

    @RequestMapping(value = "/admin")
    public String showPage() {

        return "admin";
    }
    @RequestMapping(value = "/user")
    public String showPageUser() {

        return "user";
    }
}
