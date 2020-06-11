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

/**
 * This class is controller for "index" page.
 *
 * @author Igor Ivanov
 */
@Controller
public class IndexController
{
    /**
     * This field is facade for working with tour package.
     */
    private FacadeTourPackage facadeTourPackage;
    /**
     * This field is service for user.
     */
    private UserService userService;

    /**
     * This field is selected parameter validator.
     */
    @Autowired
    @Qualifier("selectedParameterValidator")
    private Validator selectedParameterValidator;

    /**
     * Constructor which receives and assigns facade for tour package, user's service.
     *
     * @param facadeTourPackage - facade for tour package.
     * @param userService       - user's service.
     */
    @Autowired
    public IndexController(FacadeTourPackage facadeTourPackage, UserService userService)
    {
        this.facadeTourPackage = facadeTourPackage;
        this.userService = userService;
    }

    /**
     * This method handles request "/","/{page}". It sets lists of transports, food systems, tour package's types
     * and list of tour packages for pagination to session attributes. It sets tour package for order and
     * selected parameters to model attribute
     * and returns "index" page.
     *
     * @param model                             - model.
     * @param page                              - number of page,
     * @param request                           - HTTP request.
     * @param parametersSelectedForTourPackages - selected parameters for tour packages.
     * @param tourPackageForOrder               - tour package for order.
     * @return "index" page.
     * @throws ServiceException    when facade or service throws ServiceException.
     * @throws ControllerException when facade returns null values.
     */
    @RequestMapping({"/", "/{page}"})
    public String showHomePage(Model model,
                               @PathVariable(required = false, name = "page") String page,
                               HttpServletRequest request,
                               @ModelAttribute("selectsParameters")
                                       ParametersSelectedForTourPackages parametersSelectedForTourPackages,
                               @ModelAttribute("tourPackageForOrder")
                                       TourPackage tourPackageForOrder
    ) throws ServiceException, ControllerException
    {
        if (page == null || page.isEmpty())
        {
            PagedListHolder<TourPackage> tourPackagesListHolder;
            tourPackagesListHolder = new PagedListHolder<>();
            tourPackagesListHolder.setSource(facadeTourPackage.getTourPackages());
            tourPackagesListHolder.setPageSize(5);
            request.getSession().setAttribute("tourPackages", tourPackagesListHolder);
            request.getSession().setAttribute("types",
                    facadeTourPackage.getTypesOfTours().orElseThrow(() -> new ControllerException("Not found types of tour package")));
            request.getSession().setAttribute("transports",
                    facadeTourPackage.getTransportsOfTours().orElseThrow(() -> new ControllerException("Not found transports of tour package")));
            request.getSession().setAttribute("foodSystemList",
                    facadeTourPackage.getFoodSystemsOfTours().orElseThrow(() -> new ControllerException("Not found food systems of tour package")));
        }
        else
        {
            UtilController.pagination(request, page, "tourPackages");
        }
        model.addAttribute("selectsParameters", parametersSelectedForTourPackages);
        model.addAttribute("tourPackageForOrder", tourPackageForOrder);
        setDiscountForAuthorizedUser(request);
        return "index";
    }

    /**
     * This method handles request "/select". If input data is valid method sets selected tour packages to session list page holder
     * and returns "index" page. Else returns "index" page.
     *
     * @param parametersSelectedForTourPackages - selected parameters for tour packages.
     * @param result                            - binding result.
     * @param tourPackageForOrder               - tour package for order.
     * @return "index" page.
     * @throws ServiceException when facade throws ServiceException.
     */
    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String showSelectTourPackages(HttpServletRequest request,
                                         @Valid
                                         @ModelAttribute("selectsParameters")
                                                 ParametersSelectedForTourPackages parametersSelectedForTourPackages,
                                         BindingResult result,
                                         @ModelAttribute("tourPackageForOrder")
                                                 TourPackage tourPackageForOrder
    ) throws ServiceException
    {
        selectedParameterValidator.validate(parametersSelectedForTourPackages, result);
        if (result.hasErrors())
        {
            return "index";
        }
        PagedListHolder<TourPackage> tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackages");
        tourPackagesListHolder.setSource(facadeTourPackage.getSelectedTourPackages(parametersSelectedForTourPackages));
        tourPackagesListHolder.setPage(0);
        return "index";
    }

    /**
     * This method handles request "/authorization" and returns "authorization" page.
     *
     * @return "authorization" page.
     */
    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String showAuthorizationPage()
    {
        return "authorization";
    }

    /**
     * This method receives HTTP request and sets discount for authorized user.
     *
     * @param request - HTTP request.
     * @throws ServiceException    when service throws ServiceException.
     * @throws ControllerException when service returns null value.
     */
    private void setDiscountForAuthorizedUser(HttpServletRequest request) throws ServiceException, ControllerException
    {
        if (request.isUserInRole("ROLE_USER"))
        {
            request.getSession().setAttribute("discount",
                    userService.getUserByLogin(request.getUserPrincipal().getName())
                            .orElseThrow(() -> new ControllerException("Not found user")).getDiscount());
        }
    }
}
