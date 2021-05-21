package com.grap.gameandcustomtab.domain;

import com.grap.customtab.domain.CustomTab;
import com.grap.game.domain.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class GameAndCustomTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "custom_tab_id")
    private CustomTab customTab;

    public void mapGame(Game game) {
        this.game = game;
    }

    public void mapCustomTab(CustomTab customTab) {
        this.customTab = customTab;
    }
}
