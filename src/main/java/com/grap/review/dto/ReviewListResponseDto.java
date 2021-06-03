package com.grap.review.dto;

import com.grap.review.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewListResponseDto {

    private Long review_id;
    private String nickname;
    private String content;
    private int rating;
    private LocalDateTime modifiedDate;

    public ReviewListResponseDto(Review entity) {
        this.review_id = entity.getId();
        this.nickname = entity.getUser().getNickname();
        this.content = entity.getContent();
        this.rating = entity.getRating();
        this.modifiedDate = entity.getModifiedDate();
    }
}
