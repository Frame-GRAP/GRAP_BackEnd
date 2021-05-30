package com.grap.report.dto;

import com.grap.report.domain.Report;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReportListResponseDto {

    private Long id;
    private String username;
    private String reportType;
    private String content;
    private LocalDateTime modifiedDate;
    private String target;
    private Long targetId;
    private Long gameId;

    public ReportListResponseDto(Report entity) {
        this.id = entity.getId();
        this.username = entity.getUser().getName();
        this.reportType = entity.getReportType();
        this.content = entity.getContent();
        this.modifiedDate = entity.getModifiedDate();

        if(entity.getVideo() == null) {
            this.target = "review";
            this.targetId = entity.getReview().getId();
            this.gameId = entity.getReview().getGame().getId();;
        }
        else {
            this.target = "video";
            this.targetId = entity.getVideo().getId();
            this.gameId = entity.getVideo().getGame().getId();
        }

    }
}
