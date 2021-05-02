package com.grap.user.config.auth.dto;

import com.grap.user.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String loginId;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.loginId = user.getLoginId();
        this.picture = user.getPicture();
    }
}
