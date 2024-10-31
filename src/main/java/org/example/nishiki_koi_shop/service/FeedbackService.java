package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    FeedbackDto createFeedback(FeedbackDto feedbackDto);
    FeedbackDto getFeedbackById(long id);
    List<FeedbackDto> getAllFeedbacks();
    FeedbackDto updateFeedback(long id, FeedbackDto feedbackDto);
    void deleteFeedback(long id);
}
