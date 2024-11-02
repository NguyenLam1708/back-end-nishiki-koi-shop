package org.example.nishiki_koi_shop.service.impl;

import org.example.nishiki_koi_shop.model.dto.FeedbackDto;
import org.example.nishiki_koi_shop.model.entity.Feedback;
import org.example.nishiki_koi_shop.model.entity.User;
import org.example.nishiki_koi_shop.model.payload.FeedbackForm;
import org.example.nishiki_koi_shop.repository.FeedbackRepository;
import org.example.nishiki_koi_shop.repository.UserRepository;
import org.example.nishiki_koi_shop.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public FeedbackDto createFeedback(FeedbackForm feedbackForm) {
        User user = userRepository.findById(feedbackForm.getUserId()).orElseThrow(() -> new IllegalArgumentException("User co ID nay khong ton tai"));
        Feedback feedback = Feedback.builder()
                .comment(feedbackForm.getComment())
                .user(user) // Giả sử User đã tồn tại
                .tour(null) // Thiết lập nếu có tour
                .fish(null) // Thiết lập nếu có fish
                .rating(Feedback.Rating.valueOf(feedbackForm.getRating()))
                .build();
        Feedback savedFeedback = feedbackRepository.save(feedback);
//        return mapToDto(savedFeedback);
        return FeedbackDto.from(savedFeedback);
    }

    @Override
    public FeedbackDto getFeedbackById(long id) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
//        return mapToDto(feedback);
        return FeedbackDto.from(feedback);
    }

    @Override
    public List<FeedbackDto> getAllFeedbacks() {
        //return feedbackRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
        return feedbackRepository.findAll().stream()
                .map(FeedbackDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public FeedbackDto updateFeedback(long id, FeedbackForm feedbackForm) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setComment(feedbackForm.getComment());
        feedback.setRating(Feedback.Rating.valueOf(feedbackForm.getRating()));

//        //??
//        // Cập nhật user, tour và fish nếu cần
//        Feedback updatedFeedback = feedbackRepository.save(feedback);
//        return mapToDto(updatedFeedback);
        return FeedbackDto.from(feedbackRepository.save(feedback));
    }

    @Override
    public void deleteFeedback(long id) {
        feedbackRepository.deleteById(id);
    }

//    private FeedbackDto mapToDto(Feedback feedback) {
//        return FeedbackDto.builder()
//                .feedBackId(feedback.getFeedBackId())
//                .comment(feedback.getComment())
//                .createdDate(feedback.getCreatedDate())
//                .userId(feedback.getUser().getId()) // Giả sử User có phương thức getId()
//                .tourId(feedback.getTour() != null ? feedback.getTour().getId() : null)
//                .fishId(feedback.getFish() != null ? feedback.getFish().getId() : null)
//                .rating(feedback.getRating().name())
//                .build();
//    }
}
