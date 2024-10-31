package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.FishType;

@Data
@Builder
public class FishTypeDto {
    private long fishTypeId;
    private String name;
    private String description;

    public static FishTypeDto fromFishType(FishType fishType) {
        return FishTypeDto.builder()
                .fishTypeId(fishType.getFishTypeId())
                .name(fishType.getName())
                .description(fishType.getDescription())
                .build();
    }
}
