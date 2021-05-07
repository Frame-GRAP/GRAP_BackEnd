package com.grap.video.dto;

import com.grap.game.domain.Game;
import com.grap.video.domain.Video;
import lombok.Getter;

@Getter
public class VideoResponseDto {

    private Long id;
    private String title;
    private String uploader;
    private String platform;
    private String urlKey;
    private String length;
    private int liked;
    private boolean isRegistered;
    private String gameName;
    private String image;
    private Long gameId;

    public VideoResponseDto(Video entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.uploader = entity.getUploader();
        this.platform = entity.getPlatform();
        this.urlKey = entity.getUrlKey();
        this.length = entity.getLength();
        this.liked = entity.getLiked();
        this.isRegistered = entity.isRegistered();
        this.gameName = entity.getGameName();
        this.image = entity.getImage();
        this.gameId = entity.getGame().getId();
    }
}
