package org.example.nishiki_koi_shop.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackForm {

    private String comment;
    private Long userId;
    private Long tourId;
    private Long fishId;
    private String rating;
}
