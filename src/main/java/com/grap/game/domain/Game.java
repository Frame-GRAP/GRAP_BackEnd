package com.grap.game.domain;

import com.grap.categorytab.domain.CategoryTab;
import com.grap.domain.BaseTimeEntity;
import com.grap.favor.domain.Favor;
import com.grap.gameandcategory.domain.GameAndCategory;
import com.grap.review.domain.Review;
import com.grap.starter.domain.Starter;
import com.grap.video.domain.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Game extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", length = 1000, nullable = false)
    private String description;

    @Column(nullable = false)
    private String developer;

    @Column(nullable = false)
    private String publisher;

    @Column(columnDefinition = "DATE", nullable = false, name = "release_date")
    private LocalDate releaseDate;

    @Column(nullable = false, name = "header_img")
    private String headerImg;

    @Column(length = 100, nullable = false, name = "download_url")
    private String downloadUrl;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false, name = "vote_count")
    private int voteCount;

    @Column(columnDefinition = "timestamp", nullable = false)
    private LocalDateTime lastVideoCrawled;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "game")
    private List<Video> videos = new ArrayList<>();

    @OneToMany(mappedBy = "game")
    private List<Review> gameReviews = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Favor> favors = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameAndCategory> gameAndCategory = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<CategoryTab> categoryTabs = new ArrayList<>();

    @OneToOne(mappedBy = "game", cascade = CascadeType.REMOVE)
    private Starter starter;

    @PrePersist
    public void initializeColumn() {
        this.lastVideoCrawled = this.lastVideoCrawled == null ? LocalDateTime.now() : this.lastVideoCrawled;
    }

    @Builder
    public Game(String name, String description, String developer, String publisher, LocalDate releaseDate, String headerImg, String downloadUrl) {
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.headerImg = headerImg;
        this.downloadUrl = downloadUrl;
    }

    public void update(String name, String description,String downloadUrl) {
        this.name = name;
        this.description = description;
        this.downloadUrl = downloadUrl;
    }
}
