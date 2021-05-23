package com.grap.relatedgame.dto;

import com.grap.relatedgame.domain.RelatedGame;
import lombok.Getter;

@Getter
public class RelatedGameResponseDto {

    private Long id;
    private Long gameId;
    private String relatedGameId;

    public RelatedGameResponseDto(RelatedGame entity) {
        this.id = entity.getId();
        this.gameId = entity.getGame().getId();
        this.relatedGameId = entity.getRelatedGameId();
    }
}