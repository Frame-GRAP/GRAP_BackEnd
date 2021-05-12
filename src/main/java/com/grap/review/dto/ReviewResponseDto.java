package com.grap.review.dto;

import com.grap.review.domain.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String content;
    private int rating;

    public ReviewResponseDto(Review entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.rating = entity.getRating();
    }
}
