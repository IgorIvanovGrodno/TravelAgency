package com.company.controller;

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

@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    @Qualifier("userIdValidator")
    private Validator userIdValidator;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin")
    public String showPage() {
        return "admin";
    }

    @RequestMapping({"admin/set/discount/", "admin/set/discount/{page}"})
    public String showSetDiscountPage(Model model,
                                      @PathVariable(required=false, name="page") String page,
                                      HttpServletRequest request) {
        PagedListHolder<User> usersListHolder;
        if(page == null) {
            usersListHolder = new PagedListHolder<>();
            usersListHolder.setSource(userService.getAllUsers());
            usersListHolder.setPageSize(5);
            request.getSession().setAttribute("usersForSetDiscount", usersListHolder);
        }else {
            UtilController.pagination(request, page, "usersForSetDiscount");
        }
        model.addAttribute("userWithUpdateDiscount", new User());
        return "setDiscount";
    }

    @RequestMapping(value = "admin/set/discount/set", method = RequestMethod.GET)
    public String updateUser(
            @ModelAttribute("userWithUpdateDiscount") User user,
            BindingResult result
    ) throws ServiceException {
        userIdValidator.validate(user, result);
        if(result.hasErrors()){
            return "setDiscount";
        }
        userService.setDiscount(user);
        return "redirect:/admin";
    }
}
