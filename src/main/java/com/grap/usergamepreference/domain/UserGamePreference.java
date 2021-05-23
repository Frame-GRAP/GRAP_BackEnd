package com.grap.usergamepreference.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.game.domain.Game;
import com.grap.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserGamePreference extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void mapGame(Game game) {
        this.game = game;
    }

    public void mapUser(User user) {
        this.user = user;
    }
}
