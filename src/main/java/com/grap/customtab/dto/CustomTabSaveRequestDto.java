package com.grap.customtab.dto;

import com.grap.customtab.domain.CustomTab;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomTabSaveRequestDto {

    private String name;

    @Builder
    public CustomTabSaveRequestDto(String name) {
        this.name = name;
    }

    public CustomTab toEntity() {
        return CustomTab.builder()
                .name(name)
                .build();
    }
}
