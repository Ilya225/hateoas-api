package com.example.hateoasapi.utils.constraint;

import com.example.hateoasapi.utils.validator.UniqueUserValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUserValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUser {
    String message() default "Such user already exist";

    String field() default "email";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
