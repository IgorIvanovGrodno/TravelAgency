package com.company.controller;

import com.company.controller.utils.ModelTourPackage;
import com.company.model.domain.tourPackage.TourPackage;
import com.company.service.facade.FacadeTourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;

@Controller
public class CreateTourPackageController {
    private FacadeTourPackage facadeTourPackage;

    @Autowired
    public CreateTourPackageController(FacadeTourPackage facadeTourPackage) {
        this.facadeTourPackage = facadeTourPackage;
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
