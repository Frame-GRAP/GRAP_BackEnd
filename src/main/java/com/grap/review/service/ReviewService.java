package com.grap.review.service;

import com.grap.review.domain.Review;
import com.grap.review.dto.ReviewResponseDto;
import com.grap.review.dto.ReviewSaveRequestDto;
import com.grap.review.dto.ReviewUpdateRequestDto;
import com.grap.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Long save(ReviewSaveRequestDto requestDto) {
        return reviewRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ReviewUpdateRequestDto requestDto) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다. id = " +id));
        review.update(requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public ReviewResponseDto findById(Long id) {
        Review entity = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다. id = " + id));

        return new ReviewResponseDto(entity);
    }

//    @Transactional(readOnly = true)
//    public List<ReviewListResponseDto> findAllByOrderByLikeDesc() {
//        return reviewRepository.findAllByOrderByLikeDesc().stream()
//                .map(ReviewListResponseDto::new)
//                .collect(Collectors.toList());
//    }
//
//    @Transactional(readOnly = true)
//    public List<ReviewListResponseDto> findAllByOrderByDislikeDesc() {
//        return reviewRepository.findAllByOrderByDislikeDesc().stream()
//                .map(ReviewListResponseDto::new)
//                .collect(Collectors.toList());
//    }
}
