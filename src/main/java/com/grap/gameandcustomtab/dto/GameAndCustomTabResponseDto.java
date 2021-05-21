package com.grap.gameandcustomtab.dto;

import com.grap.gameandcustomtab.domain.GameAndCustomTab;
import lombok.Getter;

@Getter
public class GameAndCustomTabResponseDto {

    private Long id;
    private Long gameId;
    private String gameName;
    private Long customTabId;
    private String customTabName;

    public GameAndCustomTabResponseDto(GameAndCustomTab entity) {
        this.id = entity.getId();
        this.gameId = entity.getGame().getId();
        this.gameName = entity.getGame().getName();
        this.customTabId = entity.getCustomTab().getId();
        this.customTabName = entity.getCustomTab().getName();
    }

}
