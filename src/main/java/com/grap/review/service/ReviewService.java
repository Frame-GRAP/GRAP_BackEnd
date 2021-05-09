package com.grap.review.service;

import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.review.domain.Review;
import com.grap.review.dto.ReviewListResponseDto;
import com.grap.review.dto.ReviewSaveRequestDto;
import com.grap.review.dto.ReviewUpdateRequestDto;
import com.grap.review.repository.ReviewRepository;
import com.grap.user.config.auth.dto.SessionUser;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ReviewListResponseDto> findByGameId(Long gameId) {
        return reviewRepository.findByGameId(gameId).stream()
                .map(ReviewListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long saveReview(Long gameId, SessionUser sessionUser, ReviewSaveRequestDto requestDto) {
//        유저 주입 테스트용
//        Optional<User> opUser = userRepository.findById(Long.valueOf(1));
//        User user = opUser.get();

        User user = userRepository.findById(sessionUser.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임은 존재하지 않습니다.")
        );

        Review review = requestDto.toEntity();
        review.mapUser(user);
        review.mapGame(game);

        return reviewRepository.save(review).getId();
    }

    @Transactional
    public Long updateReview(Long reviewId, ReviewUpdateRequestDto requestDto) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다."));

        review.update(requestDto.getContent(), requestDto.getRating());

        return reviewId;
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        Review deleteReview = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당 리뷰 존재하지 않습니다.")
        );

        reviewRepository.delete(deleteReview);
    }
}
