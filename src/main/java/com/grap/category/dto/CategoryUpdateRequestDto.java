package com.grap.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryUpdateRequestDto {

    private String name;
    private String uiName;
    private String type;

    @Builder
    public CategoryUpdateRequestDto(String name, String uiName, String tyoe) {
        this.name = name;
        this.uiName = uiName;
        this.type = type;
    }
}
