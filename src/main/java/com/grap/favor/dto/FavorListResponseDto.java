package com.grap.favor.dto;

import com.grap.favor.domain.Favor;
import lombok.Getter;


@Getter
public class FavorListResponseDto {
    private Long gameId;

    public FavorListResponseDto(Favor entity) {
        this.gameId = entity.getGame().getId();
    }
}
