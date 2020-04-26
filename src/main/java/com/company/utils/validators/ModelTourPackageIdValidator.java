package com.company.utils.validators;

import com.company.utils.ModelTourPackage;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("modelTourPackageIdValidator")
public class ModelTourPackageIdValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ModelTourPackage.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ModelTourPackage modelTourPackage=(ModelTourPackage) o;
        if(modelTourPackage.getId()==null){
            errors.rejectValue("id", "null.id.tour.package");
        }
    }
}
