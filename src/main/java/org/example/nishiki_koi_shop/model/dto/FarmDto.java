package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Farm;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class FarmDto {
    private Long farmId;
    private String farmName;
    private String farmDescription;
    private String contactInfo;
    private String location;
    private LocalDate createdDate;
    private String imageUrl;

    public static FarmDto from(Farm farm) {
        return FarmDto.builder()
                .farmId(farm.getFarmId())
                .farmName(farm.getName())
                .farmDescription(farm.getDescription())
                .contactInfo(farm.getContactInfo())
                .location(farm.getLocation())
                .createdDate(farm.getCreatedDate())
                .imageUrl(farm.getImage())
                .build();
    }
}
