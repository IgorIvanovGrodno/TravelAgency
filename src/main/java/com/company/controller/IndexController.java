package com.company.controller;

import com.company.controller.utils.ParametersSelectedForTourPackages;
import com.company.model.domain.TourPackage.FoodSystem;
import com.company.model.domain.TourPackage.TourPackage;
import com.company.model.domain.TourPackage.TourPackageType;
import com.company.model.domain.TourPackage.Transport;
import com.company.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class IndexController {
    private TourPackageService tourPackageService;

    @Autowired
    public IndexController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping({"/","/{page}"})
    public ModelAndView showHomePage(@PathVariable(required=false, name="page") String page,
                                     HttpServletRequest request) {
        
        ModelAndView model = new ModelAndView();
        PagedListHolder<TourPackage> tourPackagesListHolder;
        if(page == null) {
            tourPackagesListHolder = new PagedListHolder<>();
            tourPackagesListHolder.setSource(tourPackageService.getTourPackages());
            tourPackagesListHolder.setPageSize(5);
            request.getSession().setAttribute("tourPackages", tourPackagesListHolder);
            request.getSession().setAttribute("types", TourPackageType.values());
            request.getSession().setAttribute("transports", Transport.values());
            request.getSession().setAttribute("foodSystemList", FoodSystem.values());
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
        model.setViewName("index");
        model.addObject("selectsParameters", new ParametersSelectedForTourPackages());

        return model;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String showSelectTourPackages(Model model,
                                         HttpServletRequest request,
                                         @ModelAttribute("selectsParameters")
                                         ParametersSelectedForTourPackages parametersSelectedForTourPackages,
                                         BindingResult result) {
        if(result.hasErrors()) {
            return "index";
        }
        PagedListHolder<TourPackage> tourPackagesListHolder=(PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackages");
        tourPackagesListHolder.setSource(tourPackageService.getSelectedTourPackages(parametersSelectedForTourPackages));
        tourPackagesListHolder.setPage(0);
        model.addAttribute("selectsParameters", parametersSelectedForTourPackages);
        return "index";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String showAuthorizationPage() {
        return "authorization";
    }

}
