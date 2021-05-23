package com.grap.usergamepreference.dto;

import com.grap.usergamepreference.domain.UserGamePreference;
import lombok.Getter;

@Getter
public class UserGamePreferenceResponseDto {

    private Long gameId;

    public UserGamePreferenceResponseDto(UserGamePreference entity) {
        this.gameId = entity.getGame().getId();
    }

}
