package com.grap.coupon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CouponUpdateRequestDto {

    private String name;
    private LocalDate expirationDate;

    @Builder
    public CouponUpdateRequestDto(String name, LocalDate expirationDate) {
        this.name = name;
        this.expirationDate = expirationDate;
    }
}