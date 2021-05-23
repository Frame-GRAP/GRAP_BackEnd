package com.grap.usercategorypreference.dto;

import com.grap.usercategorypreference.domain.UserCategoryPreference;
import lombok.Getter;

@Getter
public class UserCategoryPreferenceResponseDto {

    private Long categoryId;
    private String uiName;

    public UserCategoryPreferenceResponseDto(UserCategoryPreference entity) {
        this.categoryId = entity.getCategory().getId();
        this.uiName = entity.getCategory().getUiName();
    }
}
