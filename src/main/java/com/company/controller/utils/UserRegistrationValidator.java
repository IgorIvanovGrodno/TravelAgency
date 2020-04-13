package com.company.controller.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public interface UserRegistrationValidator extends Validator {

    void validate(Object o, String repeatPassword, Errors errors);
}
