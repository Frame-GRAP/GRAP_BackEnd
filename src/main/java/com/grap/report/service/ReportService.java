package com.grap.report.service;

import com.grap.report.domain.Report;
import com.grap.report.dto.ReportListResponseDto;
import com.grap.report.dto.ReportSaveRequestDto;
import com.grap.report.dto.ReportUpdateRequestDto;
import com.grap.report.repository.ReportRepository;
import com.grap.review.domain.Review;
import com.grap.review.repository.ReviewRepository;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import com.grap.video.domain.Video;
import com.grap.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Long saveVideoReport(Long userId, Long videoId, ReportSaveRequestDto requestDto) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        Video video = videoRepository.findById(videoId).orElseThrow(
                () -> new IllegalArgumentException("해당 영상은 존재하지 않습니다.")
        );

        Report report = requestDto.toEntity();
        report.mapUser(user);
        report.mapVideo(video);

        return reportRepository.save(report).getId();
    }

    @Transactional
    public Long saveReviewReport(Long userId, Long reviewId, ReportSaveRequestDto requestDto) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        Review review = reviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당 영상은 존재하지 않습니다.")
        );

        Report report = requestDto.toEntity();
        report.mapUser(user);
        report.mapReview(review);

        return reportRepository.save(report).getId();
    }

    @Transactional
    public Long updateReport(Long reportId, ReportUpdateRequestDto requestDto) {
        Report report = reportRepository.findById(reportId).orElseThrow(
                () -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다.")
        );

        report.update(requestDto.getState());

        return reportId;
    }

    @Transactional
    public void deleteReport(Long reportId) {
        Report deleteReport = reportRepository.findById(reportId).orElseThrow(
                () -> new IllegalArgumentException("해당 리뷰는 존재하지 않습니다.")
        );
        reportRepository.delete(deleteReport);
    }

    @Transactional(readOnly = true)
    public List<ReportListResponseDto> findAll() {

//        List<ReportListResponseDto> list = new ArrayList<>();
//        List<Report> reportList = reportRepository.findAll();
//        String target = "";
//
//        for(Report r : reportList) {
//            if(r.getVideo() == null)
//                list.stream().map()
//
//        }
        return reportRepository.findAll().stream()
                .map(ReportListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long countAll() {

        return reportRepository.count();
    }
}


