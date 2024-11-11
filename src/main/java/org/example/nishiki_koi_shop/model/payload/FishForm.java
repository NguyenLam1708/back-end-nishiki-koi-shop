package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FishForm {
    private String name;
    private BigDecimal price;
    private String description;
    private String image;
    private long size;
    private Integer quantity;
    private LocalDate createdDate;
    private long fishTypeId ;
    private long farmId;
}
