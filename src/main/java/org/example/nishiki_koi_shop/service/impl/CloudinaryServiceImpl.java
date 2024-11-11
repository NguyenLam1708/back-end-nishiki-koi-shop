package org.example.nishiki_koi_shop.service.impl;

import com.cloudinary.Cloudinary;
import org.example.nishiki_koi_shop.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String getPublicIdFromImgUrl(String imgUrl, String folder) {
        String baseUrl = folder + "/";
        int indexBaseUrl = imgUrl.lastIndexOf(baseUrl);
        if (indexBaseUrl != -1) {
            return imgUrl.substring(indexBaseUrl, imgUrl.lastIndexOf('.'));
        }
        return null;
    }

    @Override
    public Map<String, Object> deleteImage(String publicId) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> result = this.cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String handleUploadImg(MultipartFile file, String folder) {
        try {
            Map uploadParams = ObjectUtils.asMap(
                    "folder", folder // Specifying the folder
                    // You can add other parameters if needed, such as "public_id"
            );

            Map result = cloudinary.uploader().upload(file.getBytes(), uploadParams);
            return result.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
