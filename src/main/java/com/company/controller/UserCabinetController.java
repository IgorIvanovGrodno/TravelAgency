package com.company.controller;

import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.service.user.UserService;
import com.company.utils.UtilController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class UserCabinetController {
    private UserService userService;

    @Autowired
    public UserCabinetController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/user", "/user/{page}"})
    public String showPageUser(
            @PathVariable(required=false, name="page") String page,
            HttpServletRequest request,
            Principal principal) throws ServiceException {

        PagedListHolder<Order> ordersListHolder;
        if(page == null) {
            ordersListHolder = new PagedListHolder<>();
            ordersListHolder.setSource( userService.getUsersOrders(principal.getName()));
            ordersListHolder.setPageSize(5);
            request.getSession().setAttribute("usersOrders", ordersListHolder);
        }else {
            UtilController.pagination(request, page, "usersOrders");
        }
        return "user_cabinet";
    }
}
