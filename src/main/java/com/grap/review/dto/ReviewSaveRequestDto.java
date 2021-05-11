package com.grap.review.dto;

import com.grap.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private String content;
    private int rating;

    @Builder
    public ReviewSaveRequestDto(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .rating(rating)
                .build();
    }
}
