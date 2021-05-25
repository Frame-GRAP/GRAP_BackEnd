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

    @Builder
    public MembershipSaveRequestDto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Membership toEntity() {
        return Membership.builder()
                .name(name)
                .price(price)
                .build();
    }
}