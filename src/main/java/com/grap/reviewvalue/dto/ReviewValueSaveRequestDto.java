package com.grap.reviewvalue.dto;

import com.grap.reviewvalue.domain.ReviewValue;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewValueSaveRequestDto {

    private Boolean value;

    @Builder
    public ReviewValueSaveRequestDto(Boolean value) {
        this.value = value;
    }

    public ReviewValue toEntity() {
        return ReviewValue.builder()
                .value(value)
                .build();
    }
}
