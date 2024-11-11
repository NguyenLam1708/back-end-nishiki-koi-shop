package org.example.nishiki_koi_shop.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
