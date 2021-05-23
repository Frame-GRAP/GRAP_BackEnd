package com.grap.user.dto;

import com.grap.user.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private Boolean isRegistered;
    private String nickName;

    public UserResponseDto(User entity, boolean isRegistered) {
        this.id = entity.getId();
        this.isRegistered = isRegistered;
        this.nickName = entity.getNickname();
    }
}
