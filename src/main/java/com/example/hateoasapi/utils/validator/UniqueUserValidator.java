package com.example.hateoasapi.utils.validator;


import com.example.hateoasapi.domain.User;
import com.example.hateoasapi.repository.UserRepository;
import com.example.hateoasapi.utils.constraint.UniqueUser;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

@Component
public class UniqueUserValidator implements ConstraintValidator<Annotation, String> {

    private UserRepository userRepository;
    private UniqueUser uniqueUser;

    public UniqueUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(Annotation constraintAnnotation) {
        this.uniqueUser = (UniqueUser) constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = null;
        switch(uniqueUser.field()) {
            case "email":
                user = userRepository.findByEmail(value);
                break;
            case "username":
                user = userRepository.findByUsername(value);
                break;
            default:
                return false;
        }
        if(user != null) {
            return false;
        }
        return true;
    }
}
