package com.grap.review.dto;

import com.grap.review.domain.Review;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewListResponseDto {

    private Long id;
    private Integer likeCount;
    private Integer dislikeCount;
    private LocalDateTime modifiedDate;

    public ReviewListResponseDto(Review entity) {
        this.id = entity.getId();
        this.likeCount = entity.getLikeCount();
        this.dislikeCount = entity.getDislikeCount();
        this.modifiedDate = entity.getModifiedDate();
    }
}
