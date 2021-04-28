package com.grap.game.dto;

import com.grap.game.domain.Game;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GameResponseDto {
    private Long id;
    private String name;
    private String description;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private String headerImg;
    private String downloadUrl;
    private Double rating;

    public GameResponseDto(Game entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.developer = entity.getDeveloper();
        this.publisher = entity.getPublisher();
        this.releaseDate = entity.getReleaseDate();
        this.headerImg = entity.getHeaderImg();
        this.downloadUrl = entity.getDownloadUrl();
        this.rating = entity.getRating();
    }
}
