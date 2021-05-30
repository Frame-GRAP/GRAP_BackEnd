package com.grap.categorytab.dto;

import com.grap.categorytab.domain.CategoryTab;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CategoryTabGameResponseDto {

    private Long id;
    private String name;
    private String description;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private String headerImg;
    private String downloadUrl;

    public CategoryTabGameResponseDto(CategoryTab entity) {
        this.id = entity.getGame().getId();
        this.name = entity.getGame().getName();
        this.description = entity.getGame().getDescription();
        this.developer = entity.getGame().getDeveloper();
        this.publisher = entity.getGame().getPublisher();
        this.releaseDate = entity.getGame().getReleaseDate();
        this.headerImg = entity.getGame().getHeaderImg();
        this.downloadUrl = entity.getGame().getDownloadUrl();
    }
}
