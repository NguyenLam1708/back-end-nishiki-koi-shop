package org.example.nishiki_koi_shop.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = ObjectUtils.asMap(
                "cloud_name", "dxda6158s",
                "api_key", "723511786378225",
                "api_secret", "F317hPzx0XyzZ_NAGlwKuk8DWcY"
        );
        return new Cloudinary(config);
    }
}
