package com.grap.membership.dto;

import com.grap.membership.domain.Membership;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MembershipResponseDto {

    private Long id;
    private String name;
    private Integer price;

    public MembershipResponseDto(Membership entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.price = entity.getPrice();
    }
}
