package com.example.hateoasapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    boolean isValidImage(MultipartFile value);
}
