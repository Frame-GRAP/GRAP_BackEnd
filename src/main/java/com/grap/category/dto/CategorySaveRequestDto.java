package com.grap.category.dto;

import com.grap.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategorySaveRequestDto {

    private String name;
    private String uiName;
    private String type;

    @Builder
    public CategorySaveRequestDto(String name, String uiName, String type) {
        this.name = name;
        this.uiName = uiName;
        this.type = type;
    }

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .uiName(uiName)
                .type(type)
                .build();
    }
}
