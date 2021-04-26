package com.grap.backend.domain.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    GameRepository gameRepository;

    @AfterEach
    public void cleanup() {
        gameRepository.deleteAll();
    }

    @Test
    public void 게임저장_불러오기() {
        //given
        String name = "테스트 이름";
        String description = "테스트 설명";
        String developer = "테스트 디벨로퍼";
        String publisher = "테스트 퍼블리셔";
        LocalDate releaseDate = LocalDate.now();
        String headerImg = "테스트 이미지";
        String downloadUrl = "테스트 URL";
        Double rating = 0.5;

        gameRepository.save(Game.builder()
                .name(name)
                .description(description)
                .developer(developer)
                .publisher(publisher)
                .releaseDate(releaseDate)
                .headerImg(headerImg)
                .downloadUrl(downloadUrl)
                .rating(rating)
                .build());

        //when
        List<Game> gameList = gameRepository.findAll();

        //then
        Game game = gameList.get(0);
        assertThat(game.getName()).isEqualTo(name);
        assertThat(game.getDescription()).isEqualTo(description);
        assertThat(game.getDeveloper()).isEqualTo(developer);
        assertThat(game.getPublisher()).isEqualTo(publisher);
        assertThat(game.getReleaseDate()).isEqualTo(releaseDate);
        assertThat(game.getHeaderImg()).isEqualTo(headerImg);
        assertThat(game.getDownloadUrl()).isEqualTo(downloadUrl);
        assertThat(game.getRating()).isEqualTo(rating);
    }
}
