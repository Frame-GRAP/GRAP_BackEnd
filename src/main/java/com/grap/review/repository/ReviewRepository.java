package com.grap.review.repository;

import com.grap.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    List<Review> findAllByOrderByLikeDesc();
//
//    List<Review> findAllByOrderByDislikeDesc();
}
