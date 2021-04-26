package com.grap.backend.domain.game;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", length = 1000, nullable = false)
    private String description;

    @Column(length = 30, nullable = false)
    private String developer;

    @Column(length = 30, nullable = false)
    private String publisher;

    @Column(columnDefinition = "DATE", nullable = false, name = "release_date")
    private LocalDate releaseDate;

    @Column(nullable = false, name = "header_img")
    private String headerImg;

    @Column(length = 100, nullable = false, name = "download_url")
    private String downloadUrl;

    @Column(nullable = false)
    // 제약조건을 아직 안걸었는데, 여기서 걸면 지금 이 클래스를 통해서 DB를 새로 만들때만 적용 됨. 여기서 걸지 말고 나중에 RDS에 테이블 만들 때 걸어주면 될 듯.
    private Double rating;

    @Builder
    public Game(String name, String description, String developer, String publisher, LocalDate releaseDate, String headerImg, String downloadUrl, Double rating) {
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.headerImg = headerImg;
        this.downloadUrl = downloadUrl;
        this.rating = rating;
    }

    public void update (String name, String description, String developer, String publisher, LocalDate releaseDate, String headerImg, String downloadUrl, Double rating){
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.headerImg = headerImg;
        this.downloadUrl = downloadUrl;
        this.rating = rating;
    }
}
