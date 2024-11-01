package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Farm;
import org.example.nishiki_koi_shop.model.entity.Tour;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class TourDto {
    private long TourId;
    private String TourName;
    private String TourDescription;
    private LocalDate TourStartDate;
    private LocalDate TourEndDate;
    private int TourCapacity;
    private long TourPrice;
    private long farmId;

    public static TourDto from(Tour tour) {
        return TourDto.builder()
                .TourId(tour.getTourId())
                .TourName(tour.getName())
                .TourDescription(tour.getDescription())
                .TourStartDate(tour.getStartDate())
                .TourEndDate(tour.getEndDate())
                .TourCapacity(tour.getMaxParticipants())
                .TourPrice(tour.getPrice())
                .farmId(tour.getFarm().getFarmId())
                .build();
    }
}
