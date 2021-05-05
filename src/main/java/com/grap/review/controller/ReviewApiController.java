package com.grap.review.controller;

import com.grap.review.dto.ReviewSaveRequestDto;
import com.grap.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;

    @PostMapping("/api/review")
    public Long save(@RequestBody ReviewSaveRequestDto requestDto) {
        return reviewService.save(requestDto);
    }
}
