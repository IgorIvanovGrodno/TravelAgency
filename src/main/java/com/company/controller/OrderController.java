package com.company.controller;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.domain.user.User;
import com.company.service.facade.FacadeOrder;
import com.company.service.tourPackage.TourPackageService;
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
    private TourPackageService tourPackageService;
    private UserService userService;
    private FacadeOrder facadeOrder;

    @Autowired
    @Qualifier("tourPackageIdValidator")
    private Validator tourPackageIdValidator;

    @Autowired
    public OrderController(TourPackageService tourPackageService, UserService userService, FacadeOrder facadeOrder) {
        this.tourPackageService = tourPackageService;
        this.userService = userService;
        this.facadeOrder = facadeOrder;
    }

    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    public ModelAndView showPageOrder(HttpServletRequest request,
                                @ModelAttribute("tourPackageForOrder")TourPackage tourPackageForOrder,
                                BindingResult result,
                                @RequestParam int discount
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
        TourPackage tourPackage = tourPackageService.getTourPackage(tourPackageForOrder.getId());
        int price = tourPackage.getPrice();
        double totalPrice =price*(1-discount*0.01);
        request.getSession().setAttribute("tourPackageForOrder", tourPackage);
        modelAndView.addObject("totalPrice", (long)totalPrice);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @RequestMapping(value = "/user/order/pay", method = RequestMethod.GET)
    public String payment(
            HttpServletRequest request,
            @Valid
            @ModelAttribute("order") Order order,
            BindingResult result,
            @RequestParam long totalCost,
            Principal principal) {

        if(result.hasErrors()){
            return "forward:order";
        }
        TourPackage tourPackageOrder = (TourPackage) request.getSession().getAttribute("tourPackageForOrder");
        User currentUser = userService.getUserByLogin(principal.getName());
        facadeOrder.makePayment(order, tourPackageOrder, currentUser, totalCost);
        return "redirect:/user";
    }


}
