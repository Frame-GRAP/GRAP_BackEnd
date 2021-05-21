package com.grap.categorytab.dto;

import com.grap.categorytab.domain.CategoryTab;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryTabResponseDto {
    private Long id;
    private Long gameId;
    private String gameName;
    private Long categoryId;
    private String categoryName;

    public CategoryTabResponseDto(CategoryTab entity) {
        this.id = entity.getId();
        this.gameId = entity.getGame().getId();
        this.gameName = entity.getGame().getName();
        this.categoryId = entity.getCategory().getId();
        this.categoryName = entity.getCategory().getUiName();
    }
}