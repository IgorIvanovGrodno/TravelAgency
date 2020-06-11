package com.company.controller;

import com.company.exceptions.ControllerException;
import com.company.exceptions.ServiceException;
import com.company.model.domain.order.Order;
import com.company.model.domain.user.User;
import com.company.model.service.facade.FacadeOrder;
import com.company.model.service.user.UserService;
import com.company.utils.UtilController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * This class is controller for admin page.
 *
 * @author Igor Ivanov
 */
@Controller
public class AdminController
{
    /**
     * This field is service for user.
     */
    private UserService userService;

    /**
     * This field is order's facade.
     */
    private FacadeOrder facadeOrder;

    /**
     * This field is user's identifier validator.
     */
    @Autowired
    @Qualifier("userIdValidator")
    private Validator userIdValidator;

    /**
     * This field is message source.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Constructor which receives and assigns user's service and order's facade.
     *
     * @param userService - user's service.
     * @param facadeOrder - order's facade.
     */
    @Autowired
    public AdminController(UserService userService, FacadeOrder facadeOrder) {
        this.userService = userService;
        this.facadeOrder = facadeOrder;
    }

    /**
     * This method handles request "/admin" and returns "admin" page.
     *
     * @return admin page.
     */
    @RequestMapping(value = "/admin")
    public String showPage(HttpServletRequest request)
    {
       request.getSession().setAttribute("message",
                messageSource.getMessage("admin.hello", null,
                        RequestContextUtils.getLocaleResolver(request).resolveLocale(request)));
        return "admin";
    }

    /**
     * This method handles request "/admin/set/discount/", "/admin/set/discount/{page}", set user to model,
     * set list of users for pagination to session and returns "set_discount" page.
     *
     * @param model   - model.
     * @param page    - number of page.
     * @param request - HTTP request.
     * @return set_discount page.
     * @throws ControllerException when user's service return null value.
     */
    @RequestMapping({"/admin/set/discount/", "/admin/set/discount/{page}"})
    public String showSetDiscountPage(Model model,
                                      @PathVariable(required = false, name = "page") String page,
                                      HttpServletRequest request) throws ControllerException
    {
        if (page == null)
        {
            PagedListHolder<User> usersListHolder;
            usersListHolder = new PagedListHolder<>();
            usersListHolder.setSource(userService.getAllUsers().orElseThrow(() -> new ControllerException("Null list of users")));
            usersListHolder.setPageSize(5);
            request.getSession().setAttribute("usersForSetDiscount", usersListHolder);
        }
        else
        {
            UtilController.pagination(request, page, "usersForSetDiscount");
        }
        model.addAttribute("userWithUpdateDiscount", new User());
        return "set_discount";
    }

    /**
     * This method handles request "/admin/set/discount/set", set discount for user and
     * returns "admin" page if input data are correct else return "set_discount" page.
     *
     * @param user   - user
     * @param result - binding result.
     * @return "admin" page if input data are correct else return "set_discount" page.
     * @throws ServiceException when user's service throw ServiceException.
     */
    @RequestMapping(value = "/admin/set/discount/set", method = RequestMethod.POST)
    public String updateUserDiscount(
            @ModelAttribute("userWithUpdateDiscount") User user,
            BindingResult result, HttpServletRequest request) throws ServiceException
    {
        userIdValidator.validate(user, result);
        if (result.hasErrors())
        {
            return "set_discount";
        }
        userService.setDiscount(user);
        request.getSession().setAttribute("message",
                messageSource.getMessage("admin.success", null,
                        RequestContextUtils.getLocaleResolver(request).resolveLocale(request)));
        return "admin";
    }

    /**
     * This method handles requests "/admin/orders/", "/admin/orders/{page}",
     * set list of new orders for pagination to session and returns "change_status_order" page.
     *
     * @param page    - number of page.
     * @param request - HTTP request.
     * @return "change_status_order" page.
     */
    @RequestMapping(value = {"/admin/orders/", "/admin/orders/{page}"}, method = RequestMethod.GET)
    public String showPageChangingStatusOrder(@PathVariable(required = false, name = "page") String page,
                                              HttpServletRequest request)
    {
        if (page == null)
        {
            PagedListHolder<Order> newOrdersListHolder;
            newOrdersListHolder = new PagedListHolder<>();
            newOrdersListHolder.setSource(facadeOrder.getNewOrders());
            newOrdersListHolder.setPageSize(5);
            request.getSession().setAttribute("newOrders", newOrdersListHolder);
        }
        else
        {
            UtilController.pagination(request, page, "newOrders");
        }
        return "change_status_order";
    }

    /**
     * This method  handles request "/admin/orders/change/{id}", receives identifier of new order for changing status,
     * calls method facadeOrder.changeStatusOrder(id) and returns "admin" page.
     *
     * @param id - identifier of new order for changing status.
     * @return "admin" page.
     * @throws ServiceException when order's facade throws ServiceException.
     */
    @RequestMapping(value = "/admin/orders/change/{id}", method = RequestMethod.POST)
    public String changeStatusOrder(@PathVariable("id") Long id, HttpServletRequest request) throws ServiceException
    {
       facadeOrder.changeStatusOrder(id);
       request.getSession().setAttribute("message",
                messageSource.getMessage("admin.success", null,
                        RequestContextUtils.getLocaleResolver(request).resolveLocale(request)));
        return "admin";
    }
}
