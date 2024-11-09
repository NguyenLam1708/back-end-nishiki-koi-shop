package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Farm;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class FarmDto {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String image;
    private String contactInfo;
    private LocalDate createdDate;

    public static FarmDto from(Farm farm) {
        return FarmDto.builder()
                .id(farm.getFarmId())
                .name(farm.getName())
                .description(farm.getDescription())
                .contactInfo(farm.getContactInfo())
                .location(farm.getLocation())
                .createdDate(farm.getCreatedDate())
                .image(farm.getImage())
                .build();
    }
}
