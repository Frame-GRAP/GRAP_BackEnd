package com.grap.reviewvalue.dto;

import com.grap.reviewvalue.domain.ReviewValue;
import lombok.Getter;

@Getter
public class ReviewValueResponseDto {

    private Boolean value;

    public ReviewValueResponseDto(ReviewValue entity) {
        this.value = entity.getValue();
    }
}
