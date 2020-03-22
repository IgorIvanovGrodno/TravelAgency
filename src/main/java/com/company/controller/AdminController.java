package com.company.controller;

import com.company.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    private TourPackageService tourPackageService;

    @Autowired
    public AdminController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }



}
