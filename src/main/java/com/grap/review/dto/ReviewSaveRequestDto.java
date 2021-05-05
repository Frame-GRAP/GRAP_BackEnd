package com.grap.review.dto;

import com.grap.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ReviewSaveRequestDto {

    private String content;
    private Timestamp createdDate;

    @Builder
    public ReviewSaveRequestDto(String content, Timestamp createdDate) {
        this.content = content;
        this.createdDate = createdDate;
    }

    public Review toEntity() {
        return Review.builder()
                .content(content)
                .createdDate(createdDate)
                .build();
    }
}
