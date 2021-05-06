package com.grap.review.repository;

import com.grap.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByOrderByLikeCountDesc();

    List<Review> findAllByOrderByDislikeCountDesc();
}
