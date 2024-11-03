package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.FishType;

import java.time.LocalDate;

@Data
@Builder
public class FishTypeDto {
    private long fishTypeId;
    private String name;
    private String description;
    private LocalDate createdDate;

    public static FishTypeDto fromFishType(FishType fishType) {
        return FishTypeDto.builder()
                .fishTypeId(fishType.getFishTypeId())
                .name(fishType.getName())
                .createdDate(fishType.getCreatedDate())
                .description(fishType.getDescription())
                .build();
    }
}
