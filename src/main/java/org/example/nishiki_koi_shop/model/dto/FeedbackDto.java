package org.example.nishiki_koi_shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackDto {
    private long feedbackId;
    private String comment;
    private LocalDate createdDate;
    private long userId;
    private long tourId;
    private long fishId;
    private String rating;
}
