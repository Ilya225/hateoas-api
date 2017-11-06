package com.example.hateoasapi.service.impl;

import com.example.hateoasapi.domain.User;
import com.example.hateoasapi.domain.UserProfile;
import com.example.hateoasapi.model.UserProfileForm;
import com.example.hateoasapi.repository.UserRepository;
import com.example.hateoasapi.service.ImageService;
import com.example.hateoasapi.service.StorageService;
import com.example.hateoasapi.service.UserProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private ImageService imageService;
    private StorageService storageService;
    private UserRepository userRepository;

    public UserProfileServiceImpl(
            ImageService imageService,
            StorageService storageService,
            UserRepository userRepository
    ) {
        this.imageService = imageService;
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    @Override
    public void save(UserProfileForm profileForm) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MultipartFile avatar = profileForm.getAvatar();
        if(avatar != null && imageService.isValidImage(avatar)) {
            storageService.store(avatar);
        }

        UserProfile userProfile = new UserProfile(
                profileForm.getFirstName(),
                profileForm.getLastName(),
                profileForm.getPhone(),
                storageService.store(avatar)
        );
        user.setUserProfile(userProfile);
        userRepository.save(user);
    }
}
