package com.company.utils.validators;

import com.company.exceptions.ServiceException;
import com.company.model.domain.user.User;
import com.company.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * This class is implementation validator for registration user. It extends {@link UserRegistrationValidator}.
 *
 * @author Igor Ivanov
 */
@Component("userRegistrationValidator")
public class UserRegistrationValidatorImpl implements UserRegistrationValidator
{
    /**
     * This field is user's service.
     */
    @Autowired
    private UserService userService;

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

    @Override
    public void validate(Object o, Errors errors)
    {
    }

    /**
     * This method receives object, repeated password, errors. It validates input object. If object has invalid values or
     * object's login is already exist or password and repeated password isn't same - set rejectValue of errors.
     *
     * @param o              - input object.
     * @param repeatPassword - repeated password.
     * @param errors         - errors.
     * @throws ServiceException when service throws ServiceException.
     */
    public void validate(Object o, String repeatPassword, Errors errors) throws ServiceException
    {
        User user = (User) o;

        String firstName = user.getFirstName();
        if (firstName == null || firstName.isEmpty())
        {
            errors.rejectValue("firstName", "registration.null.empty.first.name");
        }

        String secondName = user.getSecondName();
        if (secondName == null || secondName.isEmpty())
        {
            errors.rejectValue("secondName", "registration.null.empty.second.name");
        }

        String phoneNumber = user.getPhoneNumber();
        if (phoneNumber == null || phoneNumber.isEmpty())
        {
            errors.rejectValue("phoneNumber", "registration.null.empty.number.phone");
        }
        else
        {
            if (!phoneNumber.matches("\\+\\d{12}"))
            {
                errors.rejectValue("phoneNumber", "registration.incorrect.number.phone");
            }
        }

        String email = user.getEmail();
        if (email == null || email.isEmpty())
        {
            errors.rejectValue("email", "registration.null.empty.email");
        }

        String login = user.getAuthorization().getLogin();
        if (login == null || login.isEmpty())
        {
            errors.rejectValue("authorization.login", "registration.null.empty.login");
        }
        else
        {
            if (userService.isExistUserWithLogin(login))
            {
                errors.rejectValue("authorization.login", "registration.already.exist.login");
            }
        }

        String password = user.getAuthorization().getPassword();
        if (password == null || password.isEmpty())
        {
            errors.rejectValue("authorization.password", "registration.null.empty.password");
        }
        else
        {
            if (!password.equals(repeatPassword))
            {
                errors.rejectValue("authorization.password", "registration.not.equals.password");
            }
        }
    }

    /**
     * This method return user's service.
     *
     * @return user's service.
     */
    public UserService getUserService()
    {
        return userService;
    }

    /**
     * This method set user's service.
     *
     * @param userService - user's service.
     */
    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }
}
