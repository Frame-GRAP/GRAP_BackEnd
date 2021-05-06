package com.grap.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewUpdateRequestDto {
    private String content;

    @Builder
    public ReviewUpdateRequestDto(String content) {
        this.content = content;
    }
}
