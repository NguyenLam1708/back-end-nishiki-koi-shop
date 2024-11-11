package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
public class FarmForm {
    private String name;
    private String description;
    private String location;
    private MultipartFile image;
    private String contactInfo;
//    private LocalDate createdDate;
}
