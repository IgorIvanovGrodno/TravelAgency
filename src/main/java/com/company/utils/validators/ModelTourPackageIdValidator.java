package com.company.utils.validators;

import com.company.utils.ModelTourPackage;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is validator for model tour package's id. It implements {@link org.springframework.validation.Validator}.
 *
 * @author Igor Ivanov
 */
@Component("modelTourPackageIdValidator")
public class ModelTourPackageIdValidator implements Validator
{
    /**
     * This method check type of input object.
     *
     * @param aClass - Class of input object
     * @return if type equals ModelTourPackage - true, else - false.
     */
    @Override
    public boolean supports(Class<?> aClass)
    {
        return ModelTourPackage.class.equals(aClass);
    }

    /**
     * This method validates input object. If identifier of model tour package is null then it sets rejectValue in errors.
     *
     * @param o      - input object.
     * @param errors - errors.
     */
    @Override
    public void validate(Object o, Errors errors)
    {
        ModelTourPackage modelTourPackage = (ModelTourPackage) o;
        if (modelTourPackage.getId() == null)
        {
            errors.rejectValue("id", "null.id.tour.package");
        }
    }
}
