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
    private int liked;
    private String gameName;
    private boolean isRegistered;
    private Game game;

    @Builder
    public VideoSaveRequestDto(String title, String uploader, String platform, String urlKey, String length, int liked, String gameName, boolean isRegistered) {
        this.title = title;
        this.uploader = uploader;
        this.platform = platform;
        this.urlKey = urlKey;
        this.length = length;
        this.liked = liked;
        this.gameName = gameName;
        this.isRegistered = isRegistered;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Video toEntity(){
        return Video.builder()
                .title(title)
                .uploader(uploader)
                .platform(platform)
                .urlKey(urlKey)
                .length(length)
                .liked(liked)
                .gameName(gameName)
                .isRegistered(isRegistered)
                .game(game)
                .build();
    }
}
