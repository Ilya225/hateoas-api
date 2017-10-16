package com.example.hateoasapi.utils.validator;

import com.example.hateoasapi.model.RegisterForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class PasswordValidator implements Validator {

    public boolean supports(Class c) {
        return RegisterForm.class.isAssignableFrom(c);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e,"password", "2", "Password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "confirm", "3", "Confirm field is required");

        RegisterForm userForm = (RegisterForm) obj;

        if(!userForm.getPassword().equals(userForm.getConfirm()))
        {
            e.rejectValue("password", "1","password and confirm not equal");
        }
    }
}
