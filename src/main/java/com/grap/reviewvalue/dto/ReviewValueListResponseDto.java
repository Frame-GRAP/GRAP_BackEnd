package com.grap.reviewvalue.dto;

import com.grap.reviewvalue.domain.ReviewValue;

import java.time.LocalDateTime;

public class ReviewValueListResponseDto {

    private Long id;
    private Boolean value;
    private LocalDateTime modifiedDate;

    public ReviewValueListResponseDto(ReviewValue entity) {
        this.id = entity.getId();
        this.value = entity.getValue();
        this.modifiedDate = entity.getModifiedDate();
    }
}
