package com.company.controller;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import com.company.service.facade.FacadeOrder;
import com.company.service.facade.FacadeTourPackage;
import com.company.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class OrderController {
    private FacadeTourPackage facadeTourPackage;
    private UserService userService;
    private FacadeOrder facadeOrder;

    @Autowired
    @Qualifier("tourPackageIdValidator")
    private Validator tourPackageIdValidator;

    @Autowired
    public OrderController(FacadeTourPackage facadeTourPackage, UserService userService, FacadeOrder facadeOrder) {
        this.facadeTourPackage = facadeTourPackage;
        this.userService = userService;
        this.facadeOrder = facadeOrder;
    }

    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    public ModelAndView showPageOrder(HttpServletRequest request,
                                @ModelAttribute("tourPackageForOrder")TourPackage tourPackageForOrder,
                                BindingResult result,
                                Principal principal
                                ) {
        tourPackageIdValidator.validate(tourPackageForOrder, result);
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.addObject("resultTour", result);
            modelAndView.addObject("selectsParameters", new ParametersSelectedForTourPackages());
            modelAndView.setViewName("index");
            return modelAndView;
        }
        Order order = new Order();
        TourPackage tourPackage = facadeTourPackage.getTourPackage(tourPackageForOrder.getId());
        double totalPrice = facadeTourPackage.getTotalPrice(tourPackage.getPrice(), principal);
        request.getSession().setAttribute("tourPackageForOrder", tourPackage);
        modelAndView.addObject("totalPrice", (long)totalPrice);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @RequestMapping(value = "/user/order/pay", method = RequestMethod.GET)
    public ModelAndView payment(
            HttpServletRequest request,
            @Valid
            @ModelAttribute("order") Order order,
            BindingResult result,
            @RequestParam long totalCost,
            Principal principal) {

        ModelAndView modelAndView = new ModelAndView();
        TourPackage tourPackageOrder = (TourPackage) request.getSession().getAttribute("tourPackageForOrder");
        if(result.hasErrors()){
            modelAndView.setViewName("order");
            modelAndView.addObject("tourPackageForOrder", tourPackageOrder);
            modelAndView.addObject("totalPrice", totalCost);
            return modelAndView;
        }
        User currentUser = userService.getUserByLogin(principal.getName());
        facadeOrder.makePayment(order, tourPackageOrder, currentUser, totalCost);
        modelAndView.setViewName("redirect:/user");
        return modelAndView;
    }


}
