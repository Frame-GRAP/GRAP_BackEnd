package com.grap.userandcoupon.dto;

import com.grap.userandcoupon.domain.UserAndCoupon;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserAndCouponResponseDto {
    private Long Id;
    private String couponName;
    private String code;
    private LocalDate expirationDate;
    private String gameName;
    private String gameHeaderImage;

    public UserAndCouponResponseDto(UserAndCoupon entity){
        this.Id = entity.getId();
        this.couponName = entity.getCoupon().getName();
        this.code = entity.getCode();
        this.expirationDate = entity.getCoupon().getExpirationDate();
        this.gameHeaderImage = entity.getCoupon().getGame().getHeaderImg();
        this.gameName = entity.getCoupon().getGame().getName();
    }
}
