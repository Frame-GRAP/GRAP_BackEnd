package com.grap.review.service;

import com.grap.review.domain.Review;
import com.grap.review.dto.ReviewListResponseDto;
import com.grap.review.dto.ReviewResponseDto;
import com.grap.review.dto.ReviewSaveRequestDto;
import com.grap.review.dto.ReviewUpdateRequestDto;
import com.grap.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다. id = " + id));
        reviewRepository.delete(review);
    }

    @Transactional(readOnly = true)
    public ReviewResponseDto findById(Long id) {
        Review entity = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다. id = " + id));

        return new ReviewResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ReviewListResponseDto> findAllByOrderByLikeCountDesc() {
        return reviewRepository.findAllByOrderByLikeCountDesc().stream()
                .map(ReviewListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ReviewListResponseDto> findAllByOrderByDislikeCountDesc() {
        return reviewRepository.findAllByOrderByDislikeCountDesc().stream()
                .map(ReviewListResponseDto::new)
                .collect(Collectors.toList());
    }
}
