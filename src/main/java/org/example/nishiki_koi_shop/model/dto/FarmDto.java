package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Farm;

import java.time.LocalDate;

@Data
@Builder
public class FarmDto {
    private Long farmId;
    private String name;
    private String description;
    private String location;
    private String image;
    private String contactInfo;
    private LocalDate createdDate;

    // Không cần phải viết constructor vì Lombok sẽ tự động tạo builder

    // Phương thức chuyển đổi từ Farm entity sang FarmDto
    public static FarmDto from(Farm farm) {
        return FarmDto.builder()
                .farmId(farm.getFarmId())
                .name(farm.getName())
                .description(farm.getDescription())
                .contactInfo(farm.getContactInfo())
                .location(farm.getLocation())
                .createdDate(farm.getCreatedDate())
                .image(farm.getImage())
                .build();
    }
}
