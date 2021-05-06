package com.grap.review.dto;

import com.grap.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private String content;

    @Builder
    public ReviewSaveRequestDto(String content) {
        this.content = content;
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .build();
    }
}
