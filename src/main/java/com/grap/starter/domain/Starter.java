package com.grap.starter.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.game.domain.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Starter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public void mapGame(Game game) {
        this.game = game;
    }
}
