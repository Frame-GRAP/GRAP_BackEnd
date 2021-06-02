package com.grap.membership.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MembershipUpdateRequestDto {

    private String name;
    private Integer price;
    private Integer availableCoupon;

    @Builder
    public MembershipUpdateRequestDto(String name, Integer price, Integer availableCoupon) {
        this.name = name;
        this.price = price;
        this.availableCoupon = availableCoupon;
    }
}
