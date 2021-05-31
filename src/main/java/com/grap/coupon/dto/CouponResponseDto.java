package com.grap.coupon.dto;

import com.grap.coupon.domain.Coupon;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CouponResponseDto {

    private Long couponId;
    private String couponName;
    private LocalDate expirationDate;
    private String gameHeaderImage;
    private String gameName;

    public CouponResponseDto(Coupon entity){
        this.couponId = entity.getId();
        this.couponName = entity.getName();
        this.expirationDate = entity.getExpirationDate();
        this.gameHeaderImage = entity.getGame().getHeaderImg();
        this.gameName = entity.getGame().getName();
    }
}
