package com.company.controller;

import com.company.exceptions.ControllerException;
import com.company.exceptions.ServiceException;
import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.order.Order;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.service.facade.FacadeOrder;
import com.company.model.service.facade.FacadeTourPackage;
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

/**
 * This class is controller for "order" page.
 *
 * @author Igor Ivanov
 */
@Controller
public class OrderController
{
    /**
     * This field is facade for working with tour package.
     */
    private FacadeTourPackage facadeTourPackage;
    /**
     * This field is facade for working with order.
     */
    private FacadeOrder facadeOrder;

    /**
     * This field is tour package's identifier validator.
     */
    @Autowired
    @Qualifier("tourPackageIdValidator")
    private Validator tourPackageIdValidator;

    /**
     * Constructor which receives and assigns facade for tour package, facade for order.
     *
     * @param facadeTourPackage - facade for tour package.
     * @param facadeOrder       - facade for order.
     */
    @Autowired
    public OrderController(FacadeTourPackage facadeTourPackage, FacadeOrder facadeOrder)
    {
        this.facadeTourPackage = facadeTourPackage;
        this.facadeOrder = facadeOrder;
    }

    /**
     * This method handles request "/user/order". It receives tour package for order, HTTP request, binding result and principal. If this tour package has valid
     * identifier and input binding result doesn't have errors, adds to model order and total price and returns model and "order" view.
     * Else adds to model binding result, new object of ParametersSelectedForTourPackages and returns model and "index" view.
     *
     * @param request             - HTTP request.
     * @param tourPackageForOrder - tour package for order.
     * @param result              - binding result.
     * @param principal           - principal.
     * @return if data is valid - model and "order" view, else - model and "index" view.
     * @throws ServiceException    when facade throws ServiceException.
     * @throws ControllerException when facade returns null values.
     */
    @RequestMapping(value = "/user/order", method = RequestMethod.GET)
    public ModelAndView showPageOrder(HttpServletRequest request,
                                      @ModelAttribute("tourPackageForOrder") TourPackage tourPackageForOrder,
                                      BindingResult result,
                                      Principal principal
    ) throws ServiceException, ControllerException
    {
        tourPackageIdValidator.validate(tourPackageForOrder, result);
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors())
        {
            modelAndView.addObject("resultTour", result);
            modelAndView.addObject("selectsParameters", new ParametersSelectedForTourPackages());
            modelAndView.setViewName("index");
            return modelAndView;
        }
        Order order = new Order();
        TourPackage tourPackage = facadeTourPackage.getTourPackage(tourPackageForOrder.getId()).orElseThrow(() -> new ControllerException("Not found tour package"));
        double totalPrice = facadeTourPackage.getTotalPrice(tourPackage.getPrice(), principal);
        request.getSession().setAttribute("tourPackageForOrder", tourPackage);
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("order");
        return modelAndView;
    }

    /**
     * This method handles request "/user/order/pay". It receives order, HTTP request, binding result and principal. If this order is valid
     * and input binding result doesn't have errors, makes payment and redirects to adds to "/user".
     * Else adds to model tour package and total price and returns model and "order" view.
     *
     * @param request   - HTTP request.
     * @param order     - order.
     * @param result    - binding result.
     * @param principal - principal.
     * @return if data is valid - redirect to "/user", else - model and "order" view.
     * @throws ServiceException when facade throws ServiceException.
     */
    @RequestMapping(value = "/user/order/pay", method = RequestMethod.GET)
    public ModelAndView payment(
            HttpServletRequest request,
            @Valid
            @ModelAttribute("order") Order order,
            BindingResult result,
            Principal principal) throws ServiceException
    {
        ModelAndView modelAndView = new ModelAndView();
        TourPackage tourPackageOrder = (TourPackage) request.getSession().getAttribute("tourPackageForOrder");
        if (result.hasErrors())
        {
            modelAndView.setViewName("order");
            modelAndView.addObject("tourPackageForOrder", tourPackageOrder);
            modelAndView.addObject("totalPrice", order.getTotalCost());
            return modelAndView;
        }
        facadeOrder.makePayment(order, tourPackageOrder, principal.getName());
        modelAndView.setViewName("redirect:/user");
        return modelAndView;
    }
}