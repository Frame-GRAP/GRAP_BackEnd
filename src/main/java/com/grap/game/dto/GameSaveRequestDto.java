package com.grap.game.dto;

import com.grap.game.domain.Game;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GameSaveRequestDto {
    private String name;
    private String description;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private String headerImg;
    private String downloadUrl;

    @Builder
    public GameSaveRequestDto(String name, String description, String developer, String publisher, LocalDate releaseDate, String headerImg, String downloadUrl, Double rating) {
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.headerImg = headerImg;
        this.downloadUrl = downloadUrl;
    }

    public Game toEntity(){
        return Game.builder()
                .name(name)
                .description(description)
                .developer(developer)
                .publisher(publisher)
                .releaseDate(releaseDate)
                .headerImg(headerImg)
                .downloadUrl(downloadUrl)
                .build();
    }
}

