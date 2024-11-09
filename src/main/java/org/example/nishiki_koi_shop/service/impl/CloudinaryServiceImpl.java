package org.example.nishiki_koi_shop.service.impl;

import com.cloudinary.Cloudinary;
import org.example.nishiki_koi_shop.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.utils.ObjectUtils;

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
    public String getPublicIdFromImgUrl(String imgUrl) {
        String pattern = ".*/upload/v[0-9]+/([^/.]+)";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(imgUrl);
        if (matcher.find()) {
            return matcher.group(1); // Phần publicId
        } else {
            throw new IllegalArgumentException("URL không đúng định dạng");
        }
    }

    @Override
    public Map<String, Object> deleteImage(String publicId) {
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> result = this.cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return result;
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            return null;
        }
    }
}
