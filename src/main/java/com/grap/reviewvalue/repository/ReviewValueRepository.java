package com.grap.reviewvalue.repository;

import com.grap.reviewvalue.domain.ReviewValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewValueRepository extends JpaRepository<ReviewValue, Long> {

    Long countReviewValueByReviewIdAndValueTrue(Long reviewId);
    Long countReviewValueByReviewIdAndValueFalse(Long reviewId);
    Optional<ReviewValue> findByUserIdAndReviewId(Long userId, Long reviewId);
}
