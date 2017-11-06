package com.example.hateoasapi.service.impl;

import com.example.hateoasapi.service.ImageService;
import com.example.hateoasapi.utils.ImageExtension;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public boolean isValidImage(MultipartFile value) {
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
        return true;
    }
}
