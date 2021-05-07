package com.grap.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {
    private String content;
    private int rating;

    @Builder
    public ReviewUpdateRequestDto(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }
}
