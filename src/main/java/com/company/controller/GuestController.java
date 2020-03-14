package com.company.controller;

import com.company.model.domain.TourPackage.FoodSystem;
import com.company.model.domain.TourPackage.TourPackage;
import com.company.model.domain.TourPackage.TourPackageType;
import com.company.model.domain.TourPackage.Transport;
import com.company.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GuestController {
    private TourPackageService tourPackageService;

    @Autowired
    public GuestController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping({"/","/guest"})
    public String showHomePage(Model model) {
        TourPackage tourPackage=new TourPackage();
        TourPackage tourPackage1=new TourPackage();
        tourPackage.setName("tour");
        tourPackage.setFoodSystem("ai");
        tourPackage.setTransport("bus");
        tourPackage1.setName("tour1");
        tourPackage1.setFoodSystem("ai1");
        tourPackage1.setTransport("bus1");
        List<TourPackage> list=new ArrayList<>();
        list.add(tourPackage);
        list.add(tourPackage1);

        List<FoodSystem> foods = new ArrayList<>();
        foods.add(FoodSystem.AI);
        foods.add(FoodSystem.UAI);
        foods.add(FoodSystem.BB);

        List<TourPackageType> types = new ArrayList<>();
        types.add(TourPackageType.CRUISE);
        types.add(TourPackageType.EXCURSION);
        types.add(TourPackageType.MEDICAL);
        types.add(TourPackageType.RELAXATION);
        types.add(TourPackageType.SHOPPING);

        model.addAttribute("tourPackages", list);
        model.addAttribute("foodSystemList",foods);
        model.addAttribute("foodSystem", new SelectForm());
        model.addAttribute("types", types);
        model.addAttribute("transports", Transport.values());
        return "index"; // Вернуть имя представления
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String showSelectTourPackages(@RequestParam String valueOfFoodSystem,
                                         Model model,
                                         @ModelAttribute("foodSystem") SelectForm selectForm) {

        System.out.println(selectForm);
        return "index"; // Вернуть имя представления
    }

}
