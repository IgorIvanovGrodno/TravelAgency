package com.company.controller;

import com.company.utils.ModelTourPackage;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.service.facade.FacadeTourPackage;
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
public class TourPackageController {
    private FacadeTourPackage facadeTourPackage;

    @Autowired
    @Qualifier("modelTourPackageIdValidator")
    private Validator modelTourPackageIdValidator;

    @Autowired
    public TourPackageController(FacadeTourPackage facadeTourPackage) {
        this.facadeTourPackage = facadeTourPackage;
    }

    @RequestMapping({"admin/update/tourPackage/","admin/update/tourPackage/{page}"})
    public String showUpdateTourPackagePage(Model model,
                                            @PathVariable(required=false, name="page") String page,
                                            HttpServletRequest request) {
        PagedListHolder<TourPackage> tourPackagesListHolder;
        if(page == null) {
            tourPackagesListHolder = new PagedListHolder<>();
            tourPackagesListHolder.setSource(facadeTourPackage.getTourPackages());
            tourPackagesListHolder.setPageSize(5);
            request.getSession().setAttribute("tourPackagesForUpdate", tourPackagesListHolder);
        }else if(page.equals("prev")) {
            tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackagesForUpdate");
            tourPackagesListHolder.previousPage();
        }else if(page.equals("next")) {
            tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackagesForUpdate");
            tourPackagesListHolder.nextPage();
        }else {
            int pageNum = Integer.parseInt(page);
            tourPackagesListHolder = (PagedListHolder<TourPackage>) request.getSession().getAttribute("tourPackagesForUpdate");
            tourPackagesListHolder.setPage(pageNum - 1);
        }
        model.addAttribute("updateTourPackage", new ModelTourPackage());
        return "updateTourPackage";
    }

    @RequestMapping(value = "admin/update/tourPackage/update", method = RequestMethod.GET)
    public String updateTourPackages(
            @Valid
            @ModelAttribute("updateTourPackage")
                    ModelTourPackage modelTourPackage,
            BindingResult result) {
        modelTourPackageIdValidator.validate(modelTourPackage, result);
        if(result.hasErrors()) {
            return "updateTourPackage";
        }
        facadeTourPackage.updateTourPackage(modelTourPackage);
        return "redirect:/admin";
    }

    @RequestMapping({"admin/delete/","admin/delete/{page}"})
    public String showDeleteTourPackagePage(Model model,
                                            @PathVariable(required=false, name="page") String page,
                                            HttpServletRequest request) {
        PagedListHolder<TourPackage> tourPackagesListHolder;
        if(page == null) {
            tourPackagesListHolder = new PagedListHolder<>();
            tourPackagesListHolder.setSource(facadeTourPackage.getTourPackages());
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
        model.addAttribute("deleteTourPackage", new ModelTourPackage());
        return "deleteTourPackage";
    }

    @RequestMapping(value = "admin/delete/tourPackage", method = RequestMethod.GET)
    public String deleteTourPackages(
            @ModelAttribute("deleteTourPackage")
                    ModelTourPackage modelTourPackage,
            BindingResult result) {
        modelTourPackageIdValidator.validate(modelTourPackage, result);
        if(result.hasErrors()) {
            return "deleteTourPackage";
        }
        facadeTourPackage.deleteTourPackage(modelTourPackage.getId());
        return "redirect:/admin";
    }

    @RequestMapping({"admin/create/**"})
    public String showCreateTourPackagePage(Model model) {
        model.addAttribute("newTourPackage", new ModelTourPackage());
        return "createTourPackage";
    }

    @RequestMapping(value = {"admin/create/tourPackage"}, method = RequestMethod.GET)
    public String createTourPackagePage(Model model,
                                        @Valid
                                        @ModelAttribute("newTourPackage")
                                                ModelTourPackage modelTourPackage,
                                        BindingResult result) {
        if(result.hasErrors()) {
            return "createTourPackage";
        }
        TourPackage addedTourPackage = facadeTourPackage.createTourPackage(modelTourPackage);
        return "redirect:/admin";
    }
}