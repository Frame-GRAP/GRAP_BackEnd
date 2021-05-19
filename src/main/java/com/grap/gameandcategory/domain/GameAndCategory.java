package com.grap.gameandcategory.domain;

import com.grap.category.domain.Category;
import com.grap.game.domain.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class GameAndCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public void mapGame(Game game) {
        this.game = game;
    }
    public void mapCategory(Category category) {
        this.category = category;
    }
}
