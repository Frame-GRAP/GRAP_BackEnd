package com.grap.reviewvalue.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewValueUpdateRequestDto {
    private Boolean value;

    @Builder
    public ReviewValueUpdateRequestDto(Boolean value) {
        this.value = value;
    }

}
