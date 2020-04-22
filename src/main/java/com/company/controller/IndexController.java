package com.company.controller;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.service.facadeService.FacadeTourPackageService;
import com.company.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;

@Controller
public class IndexController {
    private FacadeTourPackageService facadeTourPackageService;
    private UserService userService;

    @Autowired
    @Qualifier("selectedParameterValidator")
    private Validator selectedParameterValidator;

    @Autowired
    public IndexController(FacadeTourPackageService facadeTourPackageService, UserService userService) {
        this.facadeTourPackageService = facadeTourPackageService;
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
                               ) {

        PagedListHolder<TourPackage> tourPackagesListHolder;
        if(page == null) {
            tourPackagesListHolder = new PagedListHolder<>();
            tourPackagesListHolder.setSource(facadeTourPackageService.getTourPackages());
            tourPackagesListHolder.setPageSize(5);
            request.getSession().setAttribute("tourPackages", tourPackagesListHolder);
            request.getSession().setAttribute("types", facadeTourPackageService.getTypesOfTours());
            request.getSession().setAttribute("transports", facadeTourPackageService.getTransportsOfTours());
            request.getSession().setAttribute("foodSystemList", facadeTourPackageService.getFoodSystemsOfTours());
        }else if(page.equals("prev")) {
            tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackages");
            tourPackagesListHolder.previousPage();
        }else if(page.equals("next")) {
            tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackages");
            tourPackagesListHolder.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackages");
            tourPackagesListHolder.setPage(pageNum - 1);
        }
        model.addAttribute("selectsParameters", parametersSelectedForTourPackages);
        model.addAttribute("tourPackageForOrder", tourPackageForOrder);
        setDiscountForAuthorizedUser(model);
        return "index";
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String showSelectTourPackages(Model model,
                                         HttpServletRequest request,
                                         @Valid
                                         @ModelAttribute("selectsParameters")
                                         ParametersSelectedForTourPackages parametersSelectedForTourPackages,
                                         BindingResult result,
                                         @ModelAttribute("tourPackageForOrder")
                                                 TourPackage tourPackageForOrder
                                         ) {

        selectedParameterValidator.validate(parametersSelectedForTourPackages, result);
        if(result.hasErrors()) {
            return "index";
        }
        PagedListHolder<TourPackage> tourPackagesListHolder=(PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackages");
        tourPackagesListHolder.setSource(facadeTourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages));
        tourPackagesListHolder.setPage(0);
        setDiscountForAuthorizedUser(model);

        return "index";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String showAuthorizationPage() {
        return "authorization";
    }

    private void setDiscountForAuthorizedUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&&authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities
                    = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                    model.addAttribute("discount", userService.getUserByLogin(authentication.getName()).getDiscount());
                    break;
                }
            }
        }

    }
}
