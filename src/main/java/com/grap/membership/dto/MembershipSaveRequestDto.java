package com.grap.membership.dto;

import com.grap.membership.domain.Membership;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MembershipSaveRequestDto {

    private String name;
    private Integer price;
    private Integer availableCoupon;

    @Builder
    public MembershipSaveRequestDto(String name, Integer price, Integer availableCoupon) {
        this.name = name;
        this.price = price;
        this.availableCoupon = availableCoupon;
    }

    public Membership toEntity() {
        return Membership.builder()
                .name(name)
                .price(price)
                .availableCoupon(availableCoupon)
                .build();
    }
}