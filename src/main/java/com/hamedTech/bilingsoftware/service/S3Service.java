package com.hamedTech.bilingsoftware.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {

    String uploadFile(MultipartFile file);


    Boolean deleteFile(String imageUrl);


}
