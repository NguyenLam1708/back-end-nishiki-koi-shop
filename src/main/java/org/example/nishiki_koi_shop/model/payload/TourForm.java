package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TourForm {
    private String tourName;
    private String tourDescription;
    private Long tourPrice;
    private String tourImage;
    private LocalDate tourStartDate;
    private LocalDate tourEndDate;
    private int tourCapacity;
    private long farmId;
}
