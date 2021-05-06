package com.grap.review.dto;

import com.grap.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private String content;
//    private int like;
//    private int dislike;

    @Builder
    public ReviewSaveRequestDto(String content) { //, int like, int dislike
        this.content = content;
//        this.like = like;
//        this.dislike = dislike;
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
//                .like(like)
//                .dislike(dislike)
                .build();
    }
}
