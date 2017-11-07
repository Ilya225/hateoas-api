package com.example.hateoasapi.utils.validator;

import com.example.hateoasapi.utils.ImageExtension;
import com.example.hateoasapi.utils.constraint.Image;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

@Component
public class ImageValidator implements ConstraintValidator<Image, MultipartFile> {

    private Image imageAnno;

    @Override
    public void initialize(Image constraintAnnotation) {
        this.imageAnno = constraintAnnotation;
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        String origFilename = value.getOriginalFilename();
        ImageExtension imgExt;
        int lastDot = origFilename.lastIndexOf('.');
        if(lastDot == -1) {
            return false;
        }
        String ext = origFilename.substring(lastDot + 1).toUpperCase();
        try {
            imgExt = ImageExtension.valueOf(ext);
        } catch (IllegalArgumentException e) {
            return false;
        }
        ImageExtension[] validExts = imageAnno.extensions();

        return Arrays.asList(validExts).contains(imgExt);
    }
}
