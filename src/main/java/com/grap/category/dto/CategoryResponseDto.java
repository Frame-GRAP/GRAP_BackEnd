package com.grap.category.dto;

import com.grap.category.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String ui_name;

    public CategoryResponseDto(Category entity) {
        this.id = entity.getId();
        this.ui_name = entity.getUiName();
    }
}
