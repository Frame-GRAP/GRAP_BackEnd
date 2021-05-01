package com.grap.game.repository;

import com.grap.game.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT p FROM Game p ORDER BY p.id DESC")
    List<Game> findAllDesc();

    Optional<Game> findByName(String name);
}