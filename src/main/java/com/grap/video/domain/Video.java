package com.grap.video.domain;

import com.grap.game.domain.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String uploader;

    @Column(nullable = false)
    private String platform;

    @Column(nullable = false, name="url_key")
    private String urlKey;

    @Column(nullable = false)
    private String length;

    @Column(nullable = false)
    private int liked;

    @Column(nullable = false, name="game_name")
    private String gameName;

    @Column(nullable = false, name="is_registered")
    private boolean isRegistered;

    @ManyToOne(targetEntity = Game.class)
    @JoinColumn(name = "game_id")
    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    @Builder
    public Video(String title, String uploader, String platform, String urlKey, String length, int liked, String gameName, boolean isRegistered, Game game) {
        this.title = title;
        this.uploader = uploader;
        this.platform = platform;
        this.urlKey = urlKey;
        this.length = length;
        this.liked = liked;
        this.gameName = gameName;
        this.isRegistered = isRegistered;
        this.game = game;
    }

    public void update (String title, String uploader, String platform, String urlKey, String length, int liked){
        this.title = title;
        this.uploader = uploader;
        this.platform = platform;
        this.urlKey = urlKey;
        this.length = length;
        this.liked = liked;
    }
}
