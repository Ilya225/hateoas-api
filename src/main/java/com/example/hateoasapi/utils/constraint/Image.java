package com.example.hateoasapi.utils.constraint;

import com.example.hateoasapi.utils.ImageExtension;
import com.example.hateoasapi.utils.validator.ImageValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ImageValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Image {
    String message() default "Image is invalid";

    ImageExtension[] extensions() default {
            ImageExtension.GIF,
            ImageExtension.JPEG,
            ImageExtension.JPG,
            ImageExtension.SVG,
            ImageExtension.PNG
    };

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
