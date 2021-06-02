package com.grap.game.repository;

import com.grap.game.domain.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);
    //Page<Game> findByNameLike(String name, Pageable pageable);

    Page<Game> findByNameContainingIgnoreCase(String gameName, Pageable pageable);

}