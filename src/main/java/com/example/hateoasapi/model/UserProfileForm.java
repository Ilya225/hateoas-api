package com.example.hateoasapi.model;

import com.example.hateoasapi.utils.ImageExtension;
import com.example.hateoasapi.utils.constraint.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class UserProfileForm {

    @Size(min = 1, max = 500)
    @NotBlank
    private String firstName;

    @Size(min = 1, max = 500)
    @NotBlank
    private String lastName;

    @Image(extensions = { ImageExtension.JPG })
    private MultipartFile avatar;
}
