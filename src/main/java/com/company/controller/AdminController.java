package com.company.controller;

import com.company.exceptions.ControllerException;
import com.company.exceptions.ServiceException;
import com.company.model.domain.user.User;
import com.company.model.service.user.UserService;
import com.company.utils.UtilController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
     * This field is user's identifier validator.
     */
    @Autowired
    @Qualifier("userIdValidator")
    private Validator userIdValidator;

    /**
     * Constructor which receives and assigns user's service.
     *
     * @param userService - user's service.
     */
    @Autowired
    public AdminController(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * This method handles request "/admin" and returns "admin" page.
     *
     * @return admin page.
     */
    @RequestMapping(value = "/admin")
    public String showPage()
    {
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
        PagedListHolder<User> usersListHolder;
        if (page == null)
        {
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
    public String updateUser(
            @ModelAttribute("userWithUpdateDiscount") User user,
            BindingResult result) throws ServiceException
    {
        userIdValidator.validate(user, result);
        if (result.hasErrors())
        {
            return "set_discount";
        }
        userService.setDiscount(user);
        return "admin";
    }
}
