package com.grap.user.dto;

import com.grap.user.domain.User;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {

    private Long id;
    private String email;
    private String name;
    private String picture;
    private String nickname;

    public UserInfoResponseDto(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.picture = entity.getPicture();
        this.nickname = entity.getNickname();
    }
}