package com.company.controller;

import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.FoodSystem;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.tourPackage.TourPackageType;
import com.company.model.domain.tourPackage.Transport;
import com.company.service.order.OrderService;
import com.company.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
            Principal principal) {

        PagedListHolder<Order> ordersListHolder;
        if(page == null) {
            ordersListHolder = new PagedListHolder<>();
            ordersListHolder.setSource( userService.getUsersOrders(principal.getName()));
            ordersListHolder.setPageSize(5);
            request.getSession().setAttribute("usersOrders", ordersListHolder);
        }else if(page.equals("prev")) {
            ordersListHolder = (PagedListHolder<Order>) request.getSession().getAttribute("usersOrders");
            ordersListHolder.previousPage();
        }else if(page.equals("next")) {
            ordersListHolder = (PagedListHolder<Order>) request.getSession().getAttribute("usersOrders");
            ordersListHolder.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            ordersListHolder = (PagedListHolder<Order>) request.getSession().getAttribute("usersOrders");
            ordersListHolder.setPage(pageNum - 1);
        }
        return "userCabinet";
    }
}
