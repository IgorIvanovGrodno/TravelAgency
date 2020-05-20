package com.company.utils.validators;

import com.company.model.domain.user.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is validator for user's id. It implements {@link org.springframework.validation.Validator}.
 *
 * @author Igor Ivanov
 */
@Component("userIdValidator")
public class UserIdValidator implements Validator
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
        return User.class.equals(aClass);
    }

    /**
     * This method validates input object. If identifier of user is null then it sets rejectValue in errors.
     *
     * @param o      - input object.
     * @param errors - errors.
     */
    @Override
    public void validate(Object o, Errors errors)
    {
        User user = (User) o;
        if (user.getId() == null)
        {
            errors.rejectValue("id", "set.discount.null.id.user");
        }
    }
}
