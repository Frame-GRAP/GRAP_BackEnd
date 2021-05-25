package com.grap.membership.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MembershipUpdateRequestDto {

    private String name;
    private Integer price;

    @Builder
    public MembershipUpdateRequestDto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
