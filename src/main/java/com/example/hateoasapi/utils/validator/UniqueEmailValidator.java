package com.example.hateoasapi.utils.validator;


import com.example.hateoasapi.repository.UserRepository;
import com.example.hateoasapi.utils.constraint.UniqueEmail;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

@Component
public class UniqueEmailValidator implements ConstraintValidator<Annotation, String> {

    private UserRepository userRepository;

    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(Annotation constraintAnnotation) {
        //UniqueEmail u = (UniqueEmail) constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(userRepository.findByEmail(value) != null) {
            return false;
        }
        return true;
    }
}
