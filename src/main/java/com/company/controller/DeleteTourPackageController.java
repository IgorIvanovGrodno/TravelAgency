package com.company.controller;

import com.company.model.domain.TourPackage.TourPackage;
import com.company.service.tourPackage.TourPackageService;
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
import javax.validation.Valid;

@Controller
public class DeleteTourPackageController {
    private TourPackageService tourPackageService;

    @Autowired
    @Qualifier("tourPackageIdValidator")
    private Validator tourPackageIdValidator;

    @Autowired
    public DeleteTourPackageController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping({"admin/delete/","admin/delete/{page}"})
    public String showDeleteTourPackagePage(Model model,
                                            @PathVariable(required=false, name="page") String page,
                                            HttpServletRequest request) {
        PagedListHolder<TourPackage> tourPackagesListHolder;
        if(page == null) {
            tourPackagesListHolder = new PagedListHolder<>();
            tourPackagesListHolder.setSource(tourPackageService.getTourPackages());
            tourPackagesListHolder.setPageSize(5);
            request.getSession().setAttribute("tourPackagesForDelete", tourPackagesListHolder);
        }else if(page.equals("prev")) {
            tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackagesForDelete");
            tourPackagesListHolder.previousPage();
        }else if(page.equals("next")) {
            tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackagesForDelete");
            tourPackagesListHolder.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackagesForDelete");
            tourPackagesListHolder.setPage(pageNum - 1);
        }
        model.addAttribute("deleteTourPackage", new TourPackage());
        return "deleteTourPackage";
    }

    @RequestMapping(value = "admin/delete/tourPackage", method = RequestMethod.GET)
    public String deleteTourPackages(
            @PathVariable(required=false, name="id") Long id,
            @Valid
            @ModelAttribute("deleteTourPackage")
            TourPackage tourPackage,
            BindingResult result) {
        tourPackageIdValidator.validate(tourPackage, result);
        if(result.hasErrors()) {
            return "deleteTourPackage";
        }
        tourPackageService.deleteTourPackage(tourPackage);
        return "redirect:/admin";
    }
}
