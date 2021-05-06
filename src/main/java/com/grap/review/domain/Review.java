package com.grap.review.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.game.domain.Game;
import com.grap.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", length = 500, nullable = false)
    private String content;

//    @ColumnDefault("0")
//    @Column
//    private int like;
//
//    @ColumnDefault("0")
//    @Column
//    private int dislike;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Builder
    public Review(String content, User user, Game game) { // int like, int dislike,
        this.content = content;
//        this.like = like;
//        this.dislike = dislike;
        this.user = user;
        this.game = game;
    }

    public void update(String content) {
        this.content = content;
    }
}
