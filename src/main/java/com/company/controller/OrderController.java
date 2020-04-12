package com.company.controller;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.payment.Payment;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.service.tourPackage.TourPackageService;
import com.company.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    @Qualifier("tourPackageIdValidator")
    private Validator tourPackageIdValidator;

    @Autowired
    public OrderController(TourPackageService tourPackageService, UserService userService) {
        this.tourPackageService = tourPackageService;
        this.userService = userService;
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
        Payment payment = new Payment();
        TourPackage tourPackage = tourPackageService.getTourPackage(tourPackageForOrder.getId());
        int price = tourPackage.getPrice();
        double totalPrice =price*(1-discount*0.01);
        request.getSession().setAttribute("tourPackageForOrder", tourPackage);
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("payment", payment);
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @RequestMapping(value = "/user/order/pay", method = RequestMethod.GET)
    public String payment(
            HttpServletRequest request,
            @Valid
            @ModelAttribute("payment")Payment payment,
                                BindingResult result, Principal principal) {

        if(result.hasErrors()){
            return "forward:order";
        }
        TourPackage tourPackageOrder = (TourPackage) request.getSession().getAttribute("tourPackageForOrder");
        userService.buyTourPackage(tourPackageOrder, principal.getName());
        return "redirect:/user";
    }


}
