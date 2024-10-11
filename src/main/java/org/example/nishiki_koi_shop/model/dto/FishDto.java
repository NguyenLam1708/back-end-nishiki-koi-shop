package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Fish;

@Data
@Builder
public class FishDto {
    private Long fishId;
    private String name;
    private long price;
    private String description;
    private String image;
    private long size;
    private Integer quantity;

    public static FishDto from(Fish fish) {
        return FishDto.builder()
                .fishId(fish.getFishId())
                .name(fish.getName())
                .price(fish.getPrice())
                .description(fish.getDescription())
                .image(fish.getImage())
                .size(fish.getSize())
                .quantity(fish.getQuantity())
                .build();
    }
}
