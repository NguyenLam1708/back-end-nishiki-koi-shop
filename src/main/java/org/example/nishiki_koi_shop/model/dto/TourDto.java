package org.example.nishiki_koi_shop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.entity.Tour;

import java.time.LocalDate;

@Data
@Builder
public class TourDto {
    private Long tourId; // Changed to camelCase
    private String tourName; // Changed to camelCase
    private String tourDescription; // Changed to camelCase
    private LocalDate tourStartDate; // Changed to camelCase
    private LocalDate tourEndDate; // Changed to camelCase
    private int tourCapacity; // Changed to camelCase
    private long tourPrice; // Changed to camelCase
    private long farmId;

    // Convert from entity Tour to TourDto
    private TourDto convertToTourDto(Tour tour) {
        return TourDto.builder()
                .tourId(tour.getId()) // Sử dụng tourId thay vì id
                .tourName(tour.getName()) // Sử dụng tourName thay vì name
                .tourDescription(tour.getDescription()) // Sử dụng tourDescription thay vì description
                .tourPrice(tour.getPrice()) // Sử dụng tourPrice thay vì price
                .build();
    }

}
