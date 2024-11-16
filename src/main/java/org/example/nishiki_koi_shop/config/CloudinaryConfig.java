package org.example.nishiki_koi_shop.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

@Configuration
public class CloudinaryConfig {
    Dotenv env = Dotenv.configure().ignoreIfMissing().load();
    String cloudName = env.get("CLOUDINARY_CLOUD_NAME");
    String apiKey = env.get("CLOUDINARY_API_KEY");
    String apiSecret = env.get("CLOUDINARY_API_SECRET");

    @Bean
    public Cloudinary cloudinary() {
        Map config = ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        );
        return new Cloudinary(config);
    }
}
