package com.grap.starter.dto;

import com.grap.starter.domain.Starter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StarterResponseDto {
    private Long id;
    private Long game_id;
    private String gameName;
    private String headerImg;

    public StarterResponseDto(Starter entity) {
        this.id = entity.getId();
        this.game_id = entity.getGame().getId();
        this.gameName = entity.getGame().getName();
        this.headerImg = entity.getGame().getHeaderImg();
    }
}
