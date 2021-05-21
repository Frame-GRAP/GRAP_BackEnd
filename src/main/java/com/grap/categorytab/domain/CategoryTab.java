package com.grap.categorytab.domain;

import com.grap.category.domain.Category;
import com.grap.game.domain.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CategoryTab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(nullable = false, name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(nullable = false, name = "game_id")
    private Game game;

    public void mapCategory(Category category) {
        this.category = category;
    }

    public void mapGame(Game game) {
        this.game = game;
    }
}
