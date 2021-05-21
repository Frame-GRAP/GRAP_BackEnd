package com.grap.relatedgame.repository;

import com.grap.relatedgame.domain.RelatedGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatedGameRepository extends JpaRepository<RelatedGame, Long> {
}