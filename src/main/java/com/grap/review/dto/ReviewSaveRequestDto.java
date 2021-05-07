package com.grap.review.dto;

import com.grap.game.domain.Game;
import com.grap.review.domain.Review;
import com.grap.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private String content;
    private int rating;
    private Game game;
    private User user;

    @Builder
    public ReviewSaveRequestDto(String content, int rating, Game game, User user) {
        this.content = content;
        this.rating = rating;
        this.game = game;
        this.user = user;
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .rating(rating)
                .game(game)
                .user(user)
                .build();
    }
}
