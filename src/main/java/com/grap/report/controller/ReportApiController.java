package com.grap.report.controller;

import com.grap.report.dto.ReportListResponseDto;
import com.grap.report.dto.ReportSaveRequestDto;
import com.grap.report.dto.ReportUpdateRequestDto;
import com.grap.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class ReportApiController {

    private final ReportService reportService;

    @GetMapping("api/report/all")
    public List<ReportListResponseDto> getAllReport() {
        return reportService.findAll();
    }

    @PostMapping("/api/user/{userId}/video/{videoId}/report")
    public Long saveVideoReport(@PathVariable Long userId, @PathVariable Long videoId, @RequestBody ReportSaveRequestDto requestDto) {
        return reportService.saveVideoReport(userId, videoId, requestDto);
    }

    @PostMapping("/api/user/{userId}/review/{reviewId}/report")
    public Long saveReviewReport(@PathVariable Long userId, @PathVariable Long reviewId, @RequestBody ReportSaveRequestDto requestDto) {
        return reportService.saveReviewReport(userId, reviewId, requestDto);
    }

    @PutMapping("api/report/{reportId}") // 신고 처리 현황
    public Long updateReport(@PathVariable Long reportId, @RequestBody ReportUpdateRequestDto requestDto) {
        return reportService.updateReport(reportId, requestDto);
    }

    @DeleteMapping("/api/report/{reportId}")
    public Long deleteReview(@PathVariable Long reportId) {
        reportService.deleteReport(reportId);

        return reportId;
    }

}
