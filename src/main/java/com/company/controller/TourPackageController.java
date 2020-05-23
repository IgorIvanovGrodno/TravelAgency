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

/**
 * This class is controller for CRUD operations with tour package.
 *
 * @author Igor Ivanov
 */
@Controller
public class TourPackageController
{
    /**
     * This field is facade for working with tour package.
     */
    private FacadeTourPackage facadeTourPackage;

    /**
     * This field is model tour package's identifier validator.
     */
    @Autowired
    @Qualifier("modelTourPackageIdValidator")
    private Validator modelTourPackageIdValidator;

    /**
     * Constructor which receives and assigns facade for tour package.
     *
     * @param facadeTourPackage - facade for tour package.
     */
    @Autowired
    public TourPackageController(FacadeTourPackage facadeTourPackage)
    {
        this.facadeTourPackage = facadeTourPackage;
    }

    /**
     * This method handles request "admin/update/tourPackage/","admin/update/tourPackage/{page}". It receives model,
     * HTTP request, page. It adds list holder of tour packages to session, adds attribute "updateTourPackage" (ModelTourPackage)
     * to model and return "update_tour_package" view.
     *
     * @param model   - model.
     * @param page    - page.
     * @param request - HTTP request.
     * @return "update_tour_package" view.
     */
    @RequestMapping({"admin/update/tourPackage/", "admin/update/tourPackage/{page}"})
    public String showUpdateTourPackagePage(Model model,
                                            @PathVariable(required = false, name = "page") String page,
                                            HttpServletRequest request)
    {
        PagedListHolder<TourPackage> tourPackagesListHolder;
        if (page == null)
        {
            tourPackagesListHolder = new PagedListHolder<>();
            tourPackagesListHolder.setSource(facadeTourPackage.getTourPackages());
            tourPackagesListHolder.setPageSize(5);
            request.getSession().setAttribute("tourPackagesForUpdate", tourPackagesListHolder);
        }
        else
        {
            UtilController.pagination(request, page, "tourPackagesForUpdate");
        }
        model.addAttribute("updateTourPackage", new ModelTourPackage());
        return "update_tour_package";
    }

    /**
     * This method handles request "admin/update/tourPackage/update". It receives model of tour package, binding result. If this model has valid
     * identifier and input binding result doesn't have errors, updates tour package with same identifier with model and returns "admin" view.
     * Else returns "update_tour_package" view.
     *
     * @param modelTourPackage - model of tour package.
     * @param result           - binding result.
     * @return if data is valid - "admin" view, else - "update_tour_package" view.
     * @throws ServiceException when facade throws ServiceException.
     */
    @RequestMapping(value = "admin/update/tourPackage/update", method = RequestMethod.POST)
    public String updateTourPackages(
            @Valid
            @ModelAttribute("updateTourPackage")
                    ModelTourPackage modelTourPackage,
            BindingResult result) throws ServiceException
    {
        modelTourPackageIdValidator.validate(modelTourPackage, result);
        if (result.hasErrors())
        {
            return "update_tour_package";
        }
        facadeTourPackage.updateTourPackage(modelTourPackage);
        return "admin";
    }

    /**
     * This method handles request "admin/delete/","admin/delete/{page}". It receives model,
     * HTTP request, page. It adds list holder of tour packages to session, adds attribute "deleteTourPackage" (ModelTourPackage)
     * to model and return "delete_tour_package" view.
     *
     * @param model   - model.
     * @param page    - page.
     * @param request - HTTP request.
     * @return "delete_tour_package" view.
     */
    @RequestMapping({"admin/delete/", "admin/delete/{page}"})
    public String showDeleteTourPackagePage(Model model,
                                            @PathVariable(required = false, name = "page") String page,
                                            HttpServletRequest request)
    {
        PagedListHolder<TourPackage> tourPackagesListHolder;
        if (page == null)
        {
            tourPackagesListHolder = new PagedListHolder<>();
            tourPackagesListHolder.setSource(facadeTourPackage.getTourPackages());
            tourPackagesListHolder.setPageSize(5);
            request.getSession().setAttribute("tourPackagesForDelete", tourPackagesListHolder);
        }
        else
        {
            UtilController.pagination(request, page, "tourPackagesForDelete");
        }
        model.addAttribute("deleteTourPackage", new ModelTourPackage());
        return "delete_tour_package";
    }

    /**
     * This method handles request "admin/delete/tourPackage". It receives model of tour package, binding result. If this model has valid
     * identifier and input binding result doesn't have errors, deletes tour package with same identifier with model and returns "admin" view.
     * Else returns "delete_tour_package" view.
     *
     * @param modelTourPackage - model of tour package.
     * @param result           - binding result.
     * @return if data is valid - "admin" view, else - "delete_tour_package" view.
     * @throws ServiceException when facade throws ServiceException.
     */
    @RequestMapping(value = "admin/delete/tourPackage", method = RequestMethod.POST)
    public String deleteTourPackages(
            @ModelAttribute("deleteTourPackage")
                    ModelTourPackage modelTourPackage,
            BindingResult result) throws ServiceException
    {
        modelTourPackageIdValidator.validate(modelTourPackage, result);
        if (result.hasErrors())
        {
            return "delete_tour_package";
        }
        facadeTourPackage.deleteTourPackage(modelTourPackage.getId());
        return "admin";
    }

    /**
     * This method handles requests "admin/create/**". It receives model, adds attribute "newTourPackage" (ModelTourPackage)
     * to model and returns "create_tour_package" view.
     *
     * @param model - model.
     * @return "create_tour_package" view.
     */
    @RequestMapping({"admin/create/**"})
    public String showCreateTourPackagePage(Model model)
    {
        model.addAttribute("newTourPackage", new ModelTourPackage());
        return "create_tour_package";
    }

    /**
     * This method handles request "admin/create/tourPackage". It receives model of tour package, binding result.
     * If input binding result doesn't have errors, creates tour package according to model and returns "admin" view.
     * Else returns "create_tour_package" view.
     *
     * @param modelTourPackage - model of tour package.
     * @param result           - binding result.
     * @return if data is valid - "admin" view, else - "create_tour_package" view.
     * @throws ServiceException when facade throws ServiceException.
     */
    @RequestMapping(value = {"admin/create/tourPackage"}, method = RequestMethod.POST)
    public String createTourPackagePage(
            @Valid
            @ModelAttribute("newTourPackage")
                    ModelTourPackage modelTourPackage,
            BindingResult result) throws ServiceException
    {
        if (result.hasErrors())
        {
            return "create_tour_package";
        }
        facadeTourPackage.createTourPackage(modelTourPackage);
        return "admin";
    }
}
