package com.grap.relatedgame.repository;

import com.grap.relatedgame.domain.RelatedGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RelatedGameRepository extends JpaRepository<RelatedGame, Long> {

    @Transactional
    @Modifying
    @Query("delete from RelatedGame")
    void customDeleteAll();
}