package com.grap.game.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GameUpdateRequestDto {
    private String name;
    private String description;
    private String downloadUrl;

    @Builder
    public GameUpdateRequestDto(String name, String description, String downloadUrl) {
        this.name = name;
        this.description = description;
        this.downloadUrl = downloadUrl;
    }
}