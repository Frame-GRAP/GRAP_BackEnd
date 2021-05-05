package com.grap.review.domain;

import com.grap.game.domain.Game;
import com.grap.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", length = 500, nullable = false)
    private String content;

    @CreationTimestamp
    private Timestamp createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Builder
    public Review(String content, User user, Game game, Timestamp createdDate) {
        this.content = content;
        this.user = user;
        this.game = game;
        this.createdDate = createdDate;
    }
}
