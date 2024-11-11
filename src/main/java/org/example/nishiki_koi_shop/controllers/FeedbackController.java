package org.example.nishiki_koi_shop.controllers;

import jakarta.validation.Valid;
import org.example.nishiki_koi_shop.model.dto.FeedbackDto;
import org.example.nishiki_koi_shop.model.payload.FeedbackForm;
import org.example.nishiki_koi_shop.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDto> createFeedback(@RequestBody @Valid FeedbackForm feedbackForm) {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setComment(feedbackForm.getComment());
        feedbackDto.setUserId(feedbackForm.getUserId());
        feedbackDto.setTourId(feedbackForm.getTourId());
        feedbackDto.setFishId(feedbackForm.getFishId());
        feedbackDto.setRating(feedbackForm.getRating());

        FeedbackDto createdFeedback = feedbackService.createFeedback(feedbackForm);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDto> getFeedbackById(@PathVariable long id) {
        FeedbackDto feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks() {
        List<FeedbackDto> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable long id, @RequestBody FeedbackForm feedbackForm) {
        FeedbackDto updatedFeedback = feedbackService.updateFeedback(id, feedbackForm);
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
