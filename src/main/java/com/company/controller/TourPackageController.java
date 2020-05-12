package com.company.controller;

import com.company.exceptions.ServiceException;
import com.company.utils.ModelTourPackage;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.model.service.facade.FacadeTourPackage;
import com.company.utils.UtilController;
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
        }else {
            UtilController.pagination(request, page, "tourPackagesForUpdate");
        }
        model.addAttribute("updateTourPackage", new ModelTourPackage());
        return "update_tour_package";
    }

    @RequestMapping(value = "admin/update/tourPackage/update", method = RequestMethod.POST)
    public String updateTourPackages(
            @Valid
            @ModelAttribute("updateTourPackage")
                    ModelTourPackage modelTourPackage,
            BindingResult result) throws ServiceException {
        modelTourPackageIdValidator.validate(modelTourPackage, result);
        if(result.hasErrors()) {
            return "update_tour_package";
        }
        facadeTourPackage.updateTourPackage(modelTourPackage);
        return "admin";
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
        }else {UtilController.pagination(request, page, "tourPackagesForDelete");}
        model.addAttribute("deleteTourPackage", new ModelTourPackage());
        return "delete_tour_package";
    }

    @RequestMapping(value = "admin/delete/tourPackage", method = RequestMethod.POST)
    public String deleteTourPackages(
            @ModelAttribute("deleteTourPackage")
                    ModelTourPackage modelTourPackage,
            BindingResult result) throws ServiceException {
        modelTourPackageIdValidator.validate(modelTourPackage, result);
        if(result.hasErrors()) {
            return "delete_tour_package";
        }
        facadeTourPackage.deleteTourPackage(modelTourPackage.getId());
        return "admin";
    }

    @RequestMapping({"admin/create/**"})
    public String showCreateTourPackagePage(Model model) {
        model.addAttribute("newTourPackage", new ModelTourPackage());
        return "create_tour_package";
    }

    @RequestMapping(value = {"admin/create/tourPackage"}, method = RequestMethod.POST)
    public String createTourPackagePage(
                                        @Valid
                                        @ModelAttribute("newTourPackage")
                                                ModelTourPackage modelTourPackage,
                                        BindingResult result) throws ServiceException {
        if(result.hasErrors()) {
            return "create_tour_package";
        }
        facadeTourPackage.createTourPackage(modelTourPackage);
        return "admin";
    }
}
