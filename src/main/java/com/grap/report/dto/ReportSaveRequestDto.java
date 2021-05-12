package com.grap.report.dto;

import com.grap.report.domain.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportSaveRequestDto {

    private String reportType;
    private String content;

    @Builder
    public ReportSaveRequestDto(String reportType, String content) {
        this.reportType = reportType;
        this.content = content;
    }

    public Report toEntity() {
        return Report.builder()
                .reportType(reportType)
                .content(content)
                .build();
    }
}
