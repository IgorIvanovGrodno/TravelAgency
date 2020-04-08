package com.company.controller;

import com.company.model.domain.user.User;
import com.company.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SetDiscountController {
    private UserService userService;

    @Autowired
    public SetDiscountController(UserService userService) {
        this.userService = userService;
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
        }else if(page.equals("prev")) {
            usersListHolder = (PagedListHolder<User>) request.getSession().getAttribute("usersForSetDiscount");
            usersListHolder.previousPage();
        }else if(page.equals("next")) {
            usersListHolder = (PagedListHolder<User>) request.getSession().getAttribute("usersForSetDiscount");
            usersListHolder.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            usersListHolder = (PagedListHolder<User>) request.getSession().getAttribute("usersForSetDiscount");
            usersListHolder.setPage(pageNum - 1);
        }

        model.addAttribute("userWithUpdateDiscount", new User());
        return "setDiscount";
    }

    @RequestMapping(value = "admin/set/discount/set", method = RequestMethod.GET)
    public String updateUser(
            @Valid
            @ModelAttribute("userWithUpdateDiscount")
                    User user,
            BindingResult result
            ) {
        if(result.hasErrors()){
            return "setDiscount";
        }
        int countUpdatedUsers = userService.setDiscount(user);
        if(countUpdatedUsers>0) return "redirect:/admin";
        else {
            //Добавить вывод ошибки
            return "setDiscount";
        }
    }

}
