package com.company.controller.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component("selectedParameterValidator")
public class SelectedParameterValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ParametersSelectedForTourPackages.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ParametersSelectedForTourPackages parametersSelectedForTourPackages=(ParametersSelectedForTourPackages) o;
        String minDay =parametersSelectedForTourPackages.getMinDay();
        String maxDay =parametersSelectedForTourPackages.getMaxDay();

        if(minDay!=null&&maxDay!=null
                &&!minDay.isEmpty()&&!maxDay.isEmpty()
                &&Integer.parseInt(minDay)>Integer.parseInt(maxDay)){
            errors.rejectValue("minDay", "illegal.range.days");
        }

        String minPrice = parametersSelectedForTourPackages.getMinPrice();
        String maxPrice = parametersSelectedForTourPackages.getMaxPrice();

        if(minPrice!=null&&maxPrice!=null
                &&!minPrice.isEmpty()&&!maxPrice.isEmpty()
                &&Integer.parseInt(minPrice)>Integer.parseInt(maxPrice)){
            errors.rejectValue("minPrice", "illegal.range.prices");
        }
    }
}
