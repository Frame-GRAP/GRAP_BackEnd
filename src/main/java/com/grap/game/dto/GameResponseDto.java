package com.grap.game.dto;

import com.grap.game.domain.Game;
import com.grap.video.domain.Video;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GameResponseDto {
    private Long id;
    private String name;
    private String description;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private String headerImg;
    private String downloadUrl;
    private double rating;
    private int voteCount;
    private LocalDateTime lastVideoCrawled;
    private List<Long> videosId;

    public GameResponseDto(Game entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.developer = entity.getDeveloper();
        this.publisher = entity.getPublisher();
        this.releaseDate = entity.getReleaseDate();
        this.headerImg = entity.getHeaderImg();
        this.downloadUrl = entity.getDownloadUrl();
        this.lastVideoCrawled = entity.getLastVideoCrawled();
        this.rating = entity.getRating();
        this.voteCount = entity.getVoteCount();

        List<Video> videos = entity.getVideos();
        List<Long> videosId = new ArrayList<>();
        videos.forEach(v -> videosId.add(v.getId()));
        this.videosId = videosId;
    }
}
