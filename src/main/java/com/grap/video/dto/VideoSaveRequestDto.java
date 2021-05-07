package com.grap.video.dto;

import com.grap.game.domain.Game;
import com.grap.video.domain.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VideoSaveRequestDto {
    private String title;
    private String uploader;
    private String platform;
    private String urlKey;
    private String length;
    private String gameName;
    private String image;

    @Builder
    public VideoSaveRequestDto(String title, String uploader, String platform, String urlKey, String length, String gameName, String image) {
        this.title = title;
        this.uploader = uploader;
        this.platform = platform;
        this.urlKey = urlKey;
        this.length = length;
        this.gameName = gameName;
        this.image = image;
    }

    public Video toEntity(){
        return Video.builder()
                .title(title)
                .uploader(uploader)
                .platform(platform)
                .urlKey(urlKey)
                .length(length)
                .gameName(gameName)
                .image(image)
                .build();
    }
}
