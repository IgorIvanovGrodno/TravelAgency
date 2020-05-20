package com.company.utils.validators;

import com.company.exceptions.ServiceException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This class is interface validator for registration user. It extends {@link org.springframework.validation.Validator}.
 *
 * @author Igor Ivanov
 */
public interface UserRegistrationValidator extends Validator
{
    /**
     * This method receives object, repeated password, errors. It validates input object.
     *
     * @param o              - input object.
     * @param repeatPassword - repeated password.
     * @param errors         - errors.
     * @throws ServiceException when service throws ServiceException.
     */
    void validate(Object o, String repeatPassword, Errors errors) throws ServiceException;
}
