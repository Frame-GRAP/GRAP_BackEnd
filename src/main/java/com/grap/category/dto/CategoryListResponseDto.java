package com.grap.category.dto;

import com.grap.category.domain.Category;
import lombok.Getter;

@Getter
public class CategoryListResponseDto {

    private Long id;
    private String name;

    public CategoryListResponseDto(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}