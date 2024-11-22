package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Tour;

import java.time.LocalDate;

@Data
@Builder
public class TourDto {
    private long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String image;
    private int capacity;
    private long price;
    private long farmId;

    public static TourDto from(Tour tour) {
        return TourDto.builder()
                .id(tour.getTourId())
                .name(tour.getName())
                .description(tour.getDescription())
                .startDate(tour.getStartDate())
                .endDate(tour.getEndDate())
                .capacity(tour.getMaxParticipants())
                .price(tour.getPrice())
                .image(tour.getImage())
                .farmId(tour.getFarm().getFarmId())
                .build();
    }
}
