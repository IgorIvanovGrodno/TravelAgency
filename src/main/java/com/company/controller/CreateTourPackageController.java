package com.company.controller;

import com.company.model.domain.TourPackage.TourPackage;
import com.company.service.tourPackage.TourPackageService;
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
    private TourPackageService tourPackageService;

    @Autowired
    public CreateTourPackageController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping({"admin/create/**"})
    public String showCreateTourPackagePage(Model model) {
        model.addAttribute("newTourPackage", new TourPackage());
        return "createTourPackage";
    }

    @RequestMapping(value = {"admin/create/tourPackage"}, method = RequestMethod.GET)
    public String createTourPackagePage(Model model,
                                        @Valid
                                        @ModelAttribute("newTourPackage")
                                        TourPackage tourPackage,
                                        BindingResult result) {
        if(result.hasErrors()) {
            return "createTourPackage";
        }
        Long id =tourPackageService.createTourPackage(tourPackage);
        TourPackage addedTourPackage = tourPackageService.getTourPackage(id);
        model.addAttribute("newTourPackage", new TourPackage());
        model.addAttribute("addedTourPackage", addedTourPackage);
        return "createTourPackage";
    }

}
