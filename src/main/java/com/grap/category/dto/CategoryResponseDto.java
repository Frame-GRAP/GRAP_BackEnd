package com.grap.category.dto;

import com.grap.category.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String ui_name;
    private String type;

    public CategoryResponseDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.ui_name = entity.getUiName();
        this.type = entity.getType();
    }
}
