package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class FarmForm {
    private String name;
    private String description;
    private String location;
    private String image;
    private String contactInfo;
    private LocalDate createdDate;
    private String ownerName;  // Thêm trường ownerName
}
