package com.company.controller.utils;

import com.company.model.domain.user.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("userIdValidator")
public class UserIdValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user=(User) o;
        if(user.getId()==null){
            errors.rejectValue("id", "set.discount.null.id.user");
        }
    }
}
