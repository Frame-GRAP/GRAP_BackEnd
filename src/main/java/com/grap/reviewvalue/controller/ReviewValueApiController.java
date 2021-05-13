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

    //각 유저에 대한 get mapping도 해줘야된다

    @PostMapping("/api/user/{userId}/review/{reviewId}/reviewValue")
    public Long saveReviewValue(@PathVariable Long userId, @PathVariable Long reviewId, @RequestBody ReviewValueSaveRequestDto requestDto) {
        return reviewValueService.saveReviewValue(userId, reviewId, requestDto);
    }

    @PutMapping("/api/reviewValue/{reviewValueId}")
    public Long updateReviewValue(@PathVariable Long reviewValueId, @RequestBody ReviewValueUpdateRequestDto requestDto) {
        return reviewValueService.updateReviewValue(reviewValueId, requestDto);
    }

    @DeleteMapping("/api/reviewValue/{reviewValueId}")
    public Long deleteReviewValue(@PathVariable Long reviewValueId) {
        reviewValueService.deleteReviewValue(reviewValueId);

        return reviewValueId;
    }
}
