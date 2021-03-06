package com.grap.reviewvalue.service;

import com.grap.review.domain.Review;
import com.grap.review.repository.ReviewRepository;
import com.grap.reviewvalue.domain.ReviewValue;
import com.grap.reviewvalue.dto.ReviewValueSaveRequestDto;
import com.grap.reviewvalue.dto.ReviewValueUpdateRequestDto;
import com.grap.reviewvalue.repository.ReviewValueRepository;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewValueService {

    private final ReviewValueRepository reviewValueRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public Long countReviewValueTrue(Long reviewId) {
        return reviewValueRepository.countReviewValueByReviewIdAndValueTrue(reviewId);
    }

    @Transactional(readOnly = true)
    public Long countReviewValueFalse(Long reviewId) {
        return reviewValueRepository.countReviewValueByReviewIdAndValueFalse(reviewId);
    }

    @Transactional(readOnly = true)
    public String getReviewValue(Long userId, Long reviewId) {
        ReviewValue reviewValue = reviewValueRepository.findByUserIdAndReviewId(userId, reviewId).orElse(null);

        return reviewValue != null ? reviewValue.getValue().toString() : "null";
    }

    @Transactional
    public Long saveReviewValue(Long userId, Long reviewId, ReviewValueSaveRequestDto requestDto) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다.")
        );

        ReviewValue reviewValue = requestDto.toEntity();
        reviewValue.mapUser(user);
        reviewValue.mapReview(review);

        return reviewValueRepository.save(reviewValue).getId();
    }

    @Transactional
    public Long updateReviewValue(Long userId, Long reviewId, ReviewValueUpdateRequestDto requestDto) {
        ReviewValue reviewValue = reviewValueRepository.findByUserIdAndReviewId(userId, reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당 평가는 존재하지 않습니다.")
        );

        reviewValue.update(requestDto.getValue());

        return reviewValue.getId();
    }

    @Transactional
    public Long deleteReviewValue(Long userId, Long reviewId) {
        ReviewValue deleteReviewValue = reviewValueRepository.findByUserIdAndReviewId(userId, reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당 평가는 존재하지 않습니다.")
        );

        reviewValueRepository.delete(deleteReviewValue);

        return deleteReviewValue.getId();
    }
}
