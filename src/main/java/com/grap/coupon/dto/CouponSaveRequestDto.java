package com.grap.coupon.dto;

import com.grap.coupon.domain.Coupon;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CouponSaveRequestDto {

    private String name;
    private LocalDate expirationDate;

    @Builder
    public CouponSaveRequestDto(String name, LocalDate expirationDate){
        this.name = name;
        this.expirationDate = expirationDate;
    }

    public Coupon toEntity() {
        return Coupon.builder()
                .name(name)
                .expirationDate(expirationDate)
                .build();
    }
}
