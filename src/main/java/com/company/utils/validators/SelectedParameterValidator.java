package com.company.utils.validators;

import com.company.utils.ParametersSelectedForTourPackages;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is validator for selected parameters. It implements {@link org.springframework.validation.Validator}.
 *
 * @author Igor Ivanov
 */
@Component("selectedParameterValidator")
public class SelectedParameterValidator implements Validator
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
        return ParametersSelectedForTourPackages.class.equals(aClass);
    }

    /**
     * This method validates input object. If minDay/maxDay or minPrice/maxPrice isn't valid or minDay/minPrice more than
     * maxDay/maxPrice.
     *
     * @param o      - input object.
     * @param errors - errors.
     */
    @Override
    public void validate(Object o, Errors errors)
    {
        ParametersSelectedForTourPackages parametersSelectedForTourPackages = (ParametersSelectedForTourPackages) o;
        String minDay = parametersSelectedForTourPackages.getMinDay();
        String maxDay = parametersSelectedForTourPackages.getMaxDay();

        if (minDay != null && maxDay != null
                && !minDay.isEmpty() && !maxDay.isEmpty()
                && Integer.parseInt(minDay) > Integer.parseInt(maxDay))
        {
            errors.rejectValue("minDay", "illegal.range.days");
        }

        String minPrice = parametersSelectedForTourPackages.getMinPrice();
        String maxPrice = parametersSelectedForTourPackages.getMaxPrice();

        if (minPrice != null && maxPrice != null
                && !minPrice.isEmpty() && !maxPrice.isEmpty()
                && Integer.parseInt(minPrice) > Integer.parseInt(maxPrice))
        {
            errors.rejectValue("minPrice", "illegal.range.prices");
        }
    }
}
