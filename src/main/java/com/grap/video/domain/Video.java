package com.grap.video.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.game.domain.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
public class Video extends BaseTimeEntity {

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

    @Column(nullable = false, name="game_name")
    private String gameName;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private int liked;

    @Column(columnDefinition = "TIME", nullable = false)
    private String length;

    @Column(nullable = false, name="is_registered")
    private boolean isRegistered;

    @ManyToOne()
    @JoinColumn(name = "game_id")
    private Game game;

    public void mapGame(Game game) {
        this.game = game;
    }

    @Builder
    public Video(String title, String uploader, String platform, String urlKey, String length, String gameName, String image, Game game) {
        this.title = title;
        this.uploader = uploader;
        this.platform = platform;
        this.urlKey = urlKey;
        this.length = length;
        this.gameName = gameName;
        this.image = image;
        this.game = game;
    }
}
