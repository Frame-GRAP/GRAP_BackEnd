package com.grap.report.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.review.domain.Review;
import com.grap.user.domain.User;
import com.grap.video.domain.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@DynamicInsert
@NoArgsConstructor
@Entity
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(columnDefinition = "TEXT", length = 500, nullable = false)
    private String content;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'Accepted'")
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public void mapVideo(Video video) {
        this.video = video;
    }

    public void mapReview(Review review) {
        this.review = review;
    }

    public void mapUser(User user) {
        this.user = user;
    }

    @Builder
    public Report(String reportType, String content, String state, User user, Video video, Review review) {
        this.reportType = reportType;
        this.content = content;
        this.state = state;
        this.user = user;
        this.video = video;
        this.review = review;
    }

    public void update(String state) {
        this.state = state;
    }
}
