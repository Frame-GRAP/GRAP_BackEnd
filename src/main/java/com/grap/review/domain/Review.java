package com.grap.review.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.game.domain.Game;
import com.grap.reviewvalue.domain.ReviewValue;
import com.grap.report.domain.Report;
import com.grap.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", length = 500, nullable = false)
    private String content;

    @Column(nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "review")
    private List<Report> reportedReview = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    private List<ReviewValue> likedReview = new ArrayList<>();

    public void mapGame(Game game) {
        this.game = game;
    }

    public void mapUser(User user) {
        this.user = user;
    }

    @Builder
    public Review(String content, int rating, User user, Game game) {
        this.content = content;
        this.rating = rating;
        this.user = user;
        this.game = game;
    }

    public void update(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }
}
