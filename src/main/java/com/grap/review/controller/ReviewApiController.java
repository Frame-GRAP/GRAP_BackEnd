package com.grap.review.controller;

//import com.grap.review.dto.ReviewListResponseDto;
import com.grap.review.dto.ReviewResponseDto;
import com.grap.review.dto.ReviewSaveRequestDto;
import com.grap.review.dto.ReviewUpdateRequestDto;
import com.grap.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;

    @PostMapping("/api/review")
    public Long save(@RequestBody ReviewSaveRequestDto requestDto) {
        return reviewService.save(requestDto);
    }

    @PutMapping("/api/review/{id}")
    public Long update(@PathVariable Long id, @RequestBody ReviewUpdateRequestDto requestDto) {
        return reviewService.update(id, requestDto);
    }

    @GetMapping("/api/review/{id}")
    public ReviewResponseDto findById(@PathVariable Long id) {
        return reviewService.findById(id);
    }

//    @GetMapping("/api/review/likes")
//    public List<ReviewListResponseDto> findAllByOrderByLikeDesc() {
//        return reviewService.findAllByOrderByLikeDesc();
//    }
//
//    @GetMapping("/api/review/dislikes")
//    public List<ReviewListResponseDto> findAllByOrderByDislikeDesc() {
//        return reviewService.findAllByOrderByDislikeDesc();
//    }
}
