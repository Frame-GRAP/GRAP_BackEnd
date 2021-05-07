package com.grap.review.dto;

import com.grap.review.domain.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String content;
    private int rating;
    private Integer likeCount;
    private Integer dislikeCount;

    public ReviewResponseDto(Review entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.rating = entity.getRating();
        this.likeCount = entity.getLikeCount();
        this.dislikeCount = entity.getDislikeCount();
    }
}
