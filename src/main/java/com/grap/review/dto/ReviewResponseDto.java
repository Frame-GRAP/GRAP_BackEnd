package com.grap.review.dto;

import com.grap.review.domain.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String content;
//    private int like;
//    private int dislike;

    public ReviewResponseDto(Review entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
//        this.like = entity.getLike();
//        this.dislike = entity.getDislike();
    }
}
