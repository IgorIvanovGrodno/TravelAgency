package com.company.utils.validators;

import com.company.model.domain.user.User;
import com.company.model.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component("userRegistrationValidator")
public class UserRegistrationValidatorImpl implements UserRegistrationValidator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

    public void validate(Object o, String repeatPassword, Errors errors) {
        User user=(User) o;

        String firstName=user.getFirstName();
        if(firstName==null||firstName.isEmpty()){
            errors.rejectValue("firstName", "registration.null.empty.first.name");
        }

        String secondName =user.getSecondName();
        if(secondName==null||secondName.isEmpty()){
            errors.rejectValue("secondName", "registration.null.empty.second.name");
        }

        String phoneNumber = user.getPhoneNumber();
        if(phoneNumber==null||phoneNumber.isEmpty()){
            errors.rejectValue("phoneNumber", "registration.null.empty.number.phone");
        }else{
            if(!phoneNumber.matches("\\+\\d{12}")){
                errors.rejectValue("phoneNumber", "registration.incorrect.number.phone");
            }
        }

        String email = user.getEmail();
        if(email==null||email.isEmpty()){
            errors.rejectValue("email", "registration.null.empty.email");
        }

        String login = user.getAuthorization().getLogin();
        if(login==null||login.isEmpty()){
            errors.rejectValue("authorization.login", "registration.null.empty.login");
        }else{
            if(userService.isExistUserWithLogin(login)){
                errors.rejectValue("authorization.login", "registration.already.exist.login");
            }
        }

        String password = user.getAuthorization().getPassword();
        if(password==null||password.isEmpty()){
            errors.rejectValue("authorization.password", "registration.null.empty.password");
        }else{
            if(!password.equals(repeatPassword)){
                errors.rejectValue("authorization.password", "registration.not.equals.password");
            }
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
