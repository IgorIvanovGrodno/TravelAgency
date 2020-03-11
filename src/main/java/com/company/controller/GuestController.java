package com.company.controller;

import com.company.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class GuestController {
    private TourPackageService tourPackageService;

    @Autowired
    public GuestController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping({"/","/guest"})
    public String showHomePage(Map<String, Object> model) {
        model.put("tourPackages", tourPackageService.getTourPackages()); //Добавить сообщения в модель
        return "guest"; // Вернуть имя представления
    }

}
