package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.FeedbackDto;
import org.example.nishiki_koi_shop.model.payload.FeedbackForm;

import java.util.List;

public interface FeedbackService {
    FeedbackDto createFeedback(FeedbackForm feedbackForm);

    FeedbackDto getFeedbackById(long id);

    List<FeedbackDto> getAllFeedbacks();

    FeedbackDto updateFeedback(long id, FeedbackForm feedbackForm);

    void deleteFeedback(long id);
}
