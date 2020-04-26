package com.company.utils.validators;

import com.company.exceptions.ServiceException;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface UserRegistrationValidator extends Validator {

    void validate(Object o, String repeatPassword, Errors errors) throws ServiceException;
}
