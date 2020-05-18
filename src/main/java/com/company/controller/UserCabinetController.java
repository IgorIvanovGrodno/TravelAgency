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

/**
 * This class is controller for "user_cabinet" page.
 *
 * @author Igor Ivanov
 */
@Controller
public class UserCabinetController
{
    /**
     * This field is user's service.
     */
    private UserService userService;

    /**
     * Constructor which receives and assigns user's service.
     *
     * @param userService - user's service.
     */
    @Autowired
    public UserCabinetController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * This method handles requests "/user", "/user/{page}". It receives page for pagination, HTTP request, principal.
     * It adds list holder of user's orders to session and return "user_cabinet" view.
     *
     * @param page      - page.
     * @param request   - HTTP request.
     * @param principal - principal.
     * @return "user_cabinet" view.
     * @throws ServiceException when service throws ServiceException.
     */
    @RequestMapping(value = {"/user", "/user/{page}"})
    public String showPageUser(
            @PathVariable(required = false, name = "page") String page,
            HttpServletRequest request,
            Principal principal) throws ServiceException
    {
        PagedListHolder<Order> ordersListHolder;
        if (page == null)
        {
            ordersListHolder = new PagedListHolder<>();
            ordersListHolder.setSource(userService.getUsersOrders(principal.getName()));
            ordersListHolder.setPageSize(5);
            request.getSession().setAttribute("usersOrders", ordersListHolder);
        }
        else
        {
            UtilController.pagination(request, page, "usersOrders");
        }
        return "user_cabinet";
    }
}
