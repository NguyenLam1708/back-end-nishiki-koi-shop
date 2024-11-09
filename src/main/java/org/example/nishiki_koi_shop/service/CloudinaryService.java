package org.example.nishiki_koi_shop.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CloudinaryService {
    String getPublicIdFromImgUrl(String imgUrl);
    Map<String, Object> deleteImage(String publicId);
}
