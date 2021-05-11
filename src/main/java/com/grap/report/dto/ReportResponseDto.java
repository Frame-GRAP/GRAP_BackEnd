package com.grap.report.dto;

import com.grap.report.domain.Report;
import lombok.Getter;

@Getter
public class ReportResponseDto {

    private Long id;
    private String reportType;
    private String content;

    public ReportResponseDto(Report entity) {
        this.id = entity.getId();
        this.reportType = entity.getReportType();
        this.content = entity.getContent();
    }
}