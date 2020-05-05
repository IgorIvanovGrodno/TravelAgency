package com.company.controller;

import com.company.exceptions.ControllerException;
import com.company.exceptions.ServiceException;
import com.company.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.service.facade.FacadeTourPackage;
import com.company.model.service.user.UserService;
import com.company.utils.UtilController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class IndexController {
    private FacadeTourPackage facadeTourPackage;
    private UserService userService;

    @Autowired
    @Qualifier("selectedParameterValidator")
    private Validator selectedParameterValidator;

    @Autowired
    public IndexController(FacadeTourPackage facadeTourPackage, UserService userService) {
        this.facadeTourPackage = facadeTourPackage;
        this.userService = userService;
    }

    @RequestMapping({"/","/{page}"})
    public String showHomePage(Model model,
                               @PathVariable(required=false, name="page") String page,
                               HttpServletRequest request,
                                @ModelAttribute("selectsParameters")
                                ParametersSelectedForTourPackages parametersSelectedForTourPackages,
                               @ModelAttribute("tourPackageForOrder")
                               TourPackage tourPackageForOrder
                               ) throws ServiceException, ControllerException {


        PagedListHolder<TourPackage> tourPackagesListHolder;
        if(page == null||page.isEmpty()||page.equals("favicon")) {
            tourPackagesListHolder = new PagedListHolder<>();
            tourPackagesListHolder.setSource(facadeTourPackage.getTourPackages());
            tourPackagesListHolder.setPageSize(5);
            request.getSession().setAttribute("tourPackages", tourPackagesListHolder);
            request.getSession().setAttribute("types", facadeTourPackage.getTypesOfTours().orElseThrow(()->new ControllerException("Not found types of tour package")));
            request.getSession().setAttribute("transports", facadeTourPackage.getTransportsOfTours().orElseThrow(()->new ControllerException("Not found transports of tour package")));
            request.getSession().setAttribute("foodSystemList", facadeTourPackage.getFoodSystemsOfTours().orElseThrow(()->new ControllerException("Not found food systems of tour package")));
        }else {
            UtilController.pagination(request, page, "tourPackages");
        }
        model.addAttribute("selectsParameters", parametersSelectedForTourPackages);
        model.addAttribute("tourPackageForOrder", tourPackageForOrder);
        setDiscountForAuthorizedUser(request);
        return "index";
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String showSelectTourPackages(HttpServletRequest request,
                                         @Valid
                                         @ModelAttribute("selectsParameters")
                                         ParametersSelectedForTourPackages parametersSelectedForTourPackages,
                                         BindingResult result,
                                         @ModelAttribute("tourPackageForOrder")
                                                 TourPackage tourPackageForOrder
                                         ) throws ServiceException {

        selectedParameterValidator.validate(parametersSelectedForTourPackages, result);
        if(result.hasErrors()) {
            return "index";
        }
        PagedListHolder<TourPackage> tourPackagesListHolder=(PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackages");
        tourPackagesListHolder.setSource(facadeTourPackage.getSelectedTourPackages(parametersSelectedForTourPackages));
        tourPackagesListHolder.setPage(0);
        return "index";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String showAuthorizationPage() {
        return "authorization";
    }

    private void setDiscountForAuthorizedUser(HttpServletRequest request) throws ServiceException, ControllerException {
        if(request.isUserInRole("ROLE_USER")) {
            request.getSession().setAttribute("discount", userService.getUserByLogin(request.getUserPrincipal().getName()).orElseThrow(()->new ControllerException("Not found user")).getDiscount());
        }
    }
}
