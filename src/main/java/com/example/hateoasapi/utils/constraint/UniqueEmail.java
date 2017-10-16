package com.example.hateoasapi.utils.constraint;



import com.example.hateoasapi.utils.validator.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "email already in use";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
