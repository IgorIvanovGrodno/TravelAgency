package com.company.controller.utils;

import com.company.model.domain.TourPackage.TourPackage;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("tourPackageIdValidator")
public class TourPackageIdValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return TourPackage.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TourPackage tourPackage=(TourPackage) o;
        if(tourPackage.getId()==null){
            errors.rejectValue("id", "null.id.tour.package");
        }
    }
}
