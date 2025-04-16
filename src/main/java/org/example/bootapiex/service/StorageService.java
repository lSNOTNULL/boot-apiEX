package org.example.bootapiex.service;

import org.springframework.web.multipart.MultipartFile;

public interface StroageService {
    String upload(MultipartFile file) throws Exception;
}
