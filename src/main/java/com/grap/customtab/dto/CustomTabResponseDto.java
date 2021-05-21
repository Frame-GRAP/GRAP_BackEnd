package com.grap.customtab.dto;

import com.grap.customtab.domain.CustomTab;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomTabResponseDto {

    private Long id;
    private String name;

    public CustomTabResponseDto(CustomTab entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
