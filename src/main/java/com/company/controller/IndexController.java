package com.company.controller;

import com.company.model.domain.TourPackage.FoodSystem;
import com.company.model.domain.TourPackage.TourPackage;
import com.company.model.domain.TourPackage.TourPackageType;
import com.company.model.domain.TourPackage.Transport;
import com.company.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    private TourPackageService tourPackageService;
    private ParametersSelectingForTourPackages parametersSelectingForTourPackages;

    @Autowired
    public IndexController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping({"/","/{page}"})
    public ModelAndView showHomePage(@PathVariable(required=false, name="page") String page, HttpServletRequest request) {
        TourPackage tourPackage=new TourPackage();
        TourPackage tourPackage1=new TourPackage();
        TourPackage tourPackage2=new TourPackage();
        TourPackage tourPackage3=new TourPackage();
        tourPackage.setName("tour");
        tourPackage.setFoodSystem("ai");
        tourPackage.setTransport("bus");
        tourPackage1.setName("tour1");
        tourPackage1.setFoodSystem("ai1");
        tourPackage1.setTransport("bus1");
        tourPackage1.setName("tour2");
        tourPackage1.setFoodSystem("ai2");
        tourPackage1.setTransport("bus2");
        tourPackage1.setName("tour3");
        tourPackage1.setFoodSystem("ai3");
        tourPackage1.setTransport("bus3");
        List<TourPackage> list=new ArrayList<>();
        list.add(tourPackage);
        list.add(tourPackage1);
        list.add(tourPackage2);
        list.add(tourPackage3);


        ModelAndView model = new ModelAndView();
        PagedListHolder<TourPackage> tourPackagesListHolder;
        if(page == null) {
            tourPackagesListHolder = new PagedListHolder<TourPackage>();
            tourPackagesListHolder.setSource(list);
            tourPackagesListHolder.setPageSize(2);
            request.getSession().setAttribute("tourPackages", tourPackagesListHolder);
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
        if(request.getSession().getAttribute("setSelectAttribute")==null){
            request.getSession().setAttribute("setSelectAttribute","true");
            request.getSession().setAttribute("foodSystemList", FoodSystem.values());
            request.getSession().setAttribute("selectsParameters", parametersSelectingForTourPackages);
            request.getSession().setAttribute("types", TourPackageType.values());
            request.getSession().setAttribute("transports", Transport.values());
        }
        //model.addObject("selectsParameters", new ParametersSelectingForTourPackages());

        return model;
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public String showSelectTourPackages(Model model,
                                         @ModelAttribute("foodSystem") ParametersSelectingForTourPackages parametersSelectingForTourPackages) {

        System.out.println(parametersSelectingForTourPackages);
        return "index";
    }

}
