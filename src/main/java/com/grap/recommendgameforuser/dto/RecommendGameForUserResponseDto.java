package com.grap.recommendgameforuser.dto;

import com.grap.recommendgameforuser.domain.RecommendGameForUser;
import lombok.Getter;


@Getter
public class RecommendGameForUserResponseDto {
    private Long gameId;

    public RecommendGameForUserResponseDto(RecommendGameForUser entity) {
        this.gameId = entity.getGame().getId();
    }
}
