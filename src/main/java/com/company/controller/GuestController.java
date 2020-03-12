package com.company.controller;

import com.company.model.domain.TourPackage.TourPackage;
import com.company.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GuestController {
    private TourPackageService tourPackageService;

    @Autowired
    public GuestController(
            //TourPackageService tourPackageService
    ) {
        //this.tourPackageService = tourPackageService;
    }

    @RequestMapping({"/","/guest"})
    public String showHomePage(Map<String, Object> model) {
        TourPackage tourPackage = new TourPackage();
        tourPackage.setName("TRAVEEL");
        List<TourPackage> list = new ArrayList<>();
        list.add(tourPackage);
        model.put("tourPackages", list); //Добавить сообщения в модель
        return "guest"; // Вернуть имя представления
    }

}
