package com.grap.user.dto;

import com.grap.user.domain.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserInfoResponseDto {

    private Long id;
    private String email;
    private String name;
    private String picture;
    private String nickname;
    private String membershipName;
    private Integer price;
    private Integer availableCoupon;
    private Date nextPaymentDay;

    public UserInfoResponseDto(User entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.picture = entity.getPicture();
        this.nickname = entity.getNickname();
        if(entity.getMembership() != null) {
            this.membershipName = entity.getMembership().getName();
            this.price = entity.getMembership().getPrice();
            this.availableCoupon = entity.getAvailableCoupon();
            this.nextPaymentDay = entity.getNextPaymentDay();
        }
    }
}