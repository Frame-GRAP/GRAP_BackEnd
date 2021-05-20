package com.grap.user.dto;

import com.grap.user.domain.Role;
import com.grap.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;
    private String email;
    private String picture;
    private String nickname;

    @Builder
    public UserSaveRequestDto(String name, String email, String picture, String nickname) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.nickname = nickname;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .nickname(nickname)
                .role(Role.USER)
                .build();
    }
}