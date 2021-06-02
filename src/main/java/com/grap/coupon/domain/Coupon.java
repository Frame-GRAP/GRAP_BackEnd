package com.grap.coupon.domain;

import com.grap.game.domain.Game;
import com.grap.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate expirationDate;

    public void mapGame(Game game) {
        this.game = game;
    }

    @Builder
    public Coupon(String name, LocalDate expirationDate){
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public void update(String name, LocalDate expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
    }
}
