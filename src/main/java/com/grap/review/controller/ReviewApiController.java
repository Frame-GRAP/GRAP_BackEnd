package com.grap.review.controller;

import com.grap.review.dto.ReviewListResponseDto;
import com.grap.review.dto.ReviewResponseDto;
import com.grap.review.dto.ReviewSaveRequestDto;
import com.grap.review.dto.ReviewUpdateRequestDto;
import com.grap.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/api/game/{gameId}/review/all")
    public List<ReviewListResponseDto> getReviewByGameId(@PathVariable Long gameId) {
        return reviewService.findByGameId(gameId);
    }

    @GetMapping("/api/review/{reviewId}")
    public ReviewResponseDto findReviewById(@PathVariable Long reviewId) {
        return reviewService.findByReviewId(reviewId);
    }

    @PostMapping("/api/user/{userId}/game/{gameId}/review")
    public String saveReview(@PathVariable Long userId, @PathVariable Long gameId, @RequestBody ReviewSaveRequestDto requestDto) {
        return reviewService.saveReview(userId, gameId, requestDto);
    }

    @PutMapping("/api/review/{reviewId}")
    public Long updateReview( @PathVariable Long reviewId, @RequestBody ReviewUpdateRequestDto requestDto) {
        return reviewService.updateReview(reviewId, requestDto);
    }

    @DeleteMapping("/api/review/{reviewId}")
    public Long deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);

        return reviewId;
    }
}
