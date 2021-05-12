package com.grap.report.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportUpdateRequestDto {

    private String state;

    @Builder
    public ReportUpdateRequestDto(String state) {
        this.state = state;
    }
}
