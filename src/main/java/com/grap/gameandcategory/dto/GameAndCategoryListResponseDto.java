package com.grap.gameandcategory.dto;

import com.grap.gameandcategory.domain.GameAndCategory;
import lombok.Getter;

@Getter
public class GameAndCategoryListResponseDto {

    private Long gameId;
    private Long categoryId;

    public GameAndCategoryListResponseDto(GameAndCategory entity) {
        this.gameId = entity.getGame().getId();
        this.categoryId = entity.getCategory().getId();
    }
}
