package com.grap.reviewvalue.controller;

import com.grap.reviewvalue.dto.ReviewValueSaveRequestDto;
import com.grap.reviewvalue.dto.ReviewValueUpdateRequestDto;
import com.grap.reviewvalue.service.ReviewValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class ReviewValueApiController {

    private final ReviewValueService reviewValueService;

    @GetMapping("/api/review/{reviewId}/reviewValueTrue")
    public Long getReviewValueTrue(@PathVariable Long reviewId) {
        return reviewValueService.countReviewValueTrue(reviewId);
    }

    @GetMapping("/api/review/{reviewId}/reviewValueFalse")
    public Long getReviewValueFalse(@PathVariable Long reviewId) {
        return reviewValueService.countReviewValueFalse(reviewId);
    }

    @GetMapping("api/user/{userId}/review/{reviewId}/reviewValue")
    public String getReviewValue(@PathVariable Long userId, @PathVariable Long reviewId) {
        return reviewValueService.getReviewValue(userId, reviewId);
    }

    @PostMapping("/api/user/{userId}/review/{reviewId}/reviewValue")
    public Long saveReviewValue(@PathVariable Long userId, @PathVariable Long reviewId, @RequestBody ReviewValueSaveRequestDto requestDto) {
        return reviewValueService.saveReviewValue(userId, reviewId, requestDto);
    }

    @PutMapping("/api/user/{userId}/review/{reviewId}/reviewValue")
    public Long updateReviewValue(@PathVariable Long userId, @PathVariable Long reviewId, @RequestBody ReviewValueUpdateRequestDto requestDto) {
        return reviewValueService.updateReviewValue(userId, reviewId, requestDto);
    }

    @DeleteMapping("/api/user/{userId}/review/{reviewId}/reviewValue")
    public Long deleteReviewValue(@PathVariable Long userId, @PathVariable Long reviewId) {
        return reviewValueService.deleteReviewValue(userId, reviewId);
    }
}
