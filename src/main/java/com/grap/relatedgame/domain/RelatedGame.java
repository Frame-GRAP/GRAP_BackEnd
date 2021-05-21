package com.grap.relatedgame.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.game.domain.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class RelatedGame extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Column
    private String relatedGameId;

    @Builder
    public RelatedGame (String relatedGameId){
        this.relatedGameId = relatedGameId;
    }

    public void mapGame(Game game) {
        this.game = game;
    }
}
