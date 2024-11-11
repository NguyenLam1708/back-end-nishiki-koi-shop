package org.example.nishiki_koi_shop.model.payload;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
public class TourForm {
    private String name;
    private String description;
    private Long price;
    private MultipartFile image;
    private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;
    private long farmId;
}
