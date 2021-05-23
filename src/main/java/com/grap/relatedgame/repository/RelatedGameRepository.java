package com.grap.relatedgame.repository;

import com.grap.relatedgame.domain.RelatedGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RelatedGameRepository extends JpaRepository<RelatedGame, Long> {

    Optional<RelatedGame> findByGameId(Long gameId);
}