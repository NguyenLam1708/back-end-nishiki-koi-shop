package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FishTypeForm {
    private String name;
    private String description;
}
