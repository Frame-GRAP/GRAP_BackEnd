package com.grap.review.controller;

import com.grap.review.dto.ReviewListResponseDto;
import com.grap.review.dto.ReviewSaveRequestDto;
import com.grap.review.dto.ReviewUpdateRequestDto;
import com.grap.review.service.ReviewService;
import com.grap.user.config.auth.LoginUser;
import com.grap.user.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/game/{gameId}")
@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/review/all")
    public List<ReviewListResponseDto> getReviewByGameId(@PathVariable Long gameId) {
        return reviewService.findByGameId(gameId);
    }

    @PostMapping("/review")
    public Long saveReview(@PathVariable Long gameId, @LoginUser SessionUser user, @RequestBody ReviewSaveRequestDto requestDto) {
        return reviewService.saveReview(gameId, user, requestDto);
    }

    @PutMapping("/review/{reviewId}")
    public Long updateReview(@PathVariable Long reviewId, @RequestBody ReviewUpdateRequestDto requestDto) {
        return reviewService.updateReview(reviewId, requestDto);
    }

    @DeleteMapping("/review/{reviewId}")
    public Long deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);

        return reviewId;
    }
}
