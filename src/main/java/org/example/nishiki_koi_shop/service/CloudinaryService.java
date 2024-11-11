package org.example.nishiki_koi_shop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public interface CloudinaryService {
    String getPublicIdFromImgUrl(String imgUrl, String folder);

    Map<String, Object> deleteImage(String publicId);

    String handleUploadImg(MultipartFile file, String folder);
}
