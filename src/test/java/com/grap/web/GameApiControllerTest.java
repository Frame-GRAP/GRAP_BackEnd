package com.grap.web;

import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.game.dto.GameSaveRequestDto;
import com.grap.game.dto.GameUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testng.annotations.Ignore;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GameRepository gameRepository;

    @AfterEach
    public void tearDown() throws Exception {
        gameRepository.deleteAll();
    }

    @Test
    public void Game_등록된다() throws Exception {
        //given
        String name = "테스트 이름";
        String description = "테스트 설명";
        String developer = "테스트 디벨로퍼";
        String publisher = "테스트 퍼블리셔";
        LocalDate releaseDate = LocalDate.now();
        String headerImg = "테스트 이미지";
        String downloadUrl = "테스트 URL";

        GameSaveRequestDto requestDto = GameSaveRequestDto.builder()
                .name(name)
                .description(description)
                .developer(developer)
                .publisher(publisher)
                .releaseDate(releaseDate)
                .headerImg(headerImg)
                .downloadUrl(downloadUrl)
                .build();

        String url = "http://localhost:" + port + "/api/game";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Game> all = gameRepository.findAll();

        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getDescription()).isEqualTo(description);
        assertThat(all.get(0).getDeveloper()).isEqualTo(developer);
        assertThat(all.get(0).getPublisher()).isEqualTo(publisher);
        assertThat(all.get(0).getReleaseDate()).isEqualTo(releaseDate);
        assertThat(all.get(0).getHeaderImg()).isEqualTo(headerImg);
        assertThat(all.get(0).getDownloadUrl()).isEqualTo(downloadUrl);
        assertThat(all.get(0).getRating()).isEqualTo(0.0d);
    }

    @Test
    public void Game_수정된다() throws Exception {
        Game savedGame = gameRepository.save(Game.builder()
                .name("name")
                .description("description")
                .developer("developer")
                .publisher("publisher")
                .releaseDate(LocalDate.now())
                .headerImg("headerImg")
                .downloadUrl("downloadUrl")
                .build());

        Long updateId = savedGame.getId();
        String fixedName = "fixedName";
        String fixedDescription = "fixedDescription";
        String fixedDeveloper = "fixedDeveloper";
        String fixedPublisher = "fixedPublisher";
        LocalDate fixedReleaseDate = LocalDate.of(1996, 05, 17);
        String fixedHeaderImg = "fixedHeaderImg";
        String fixedDownloadUrl = "fixedDownloadUrl";
        Double fixedRating = 0.2;

        GameUpdateRequestDto requestDto = GameUpdateRequestDto.builder()
                .name(fixedName)
                .description(fixedDescription)
                .developer(fixedDeveloper)
                .publisher(fixedPublisher)
                .releaseDate(fixedReleaseDate)
                .headerImg(fixedHeaderImg)
                .downloadUrl(fixedDownloadUrl)
                .rating(fixedRating)
                .build();

        String url = "http://localhost:" + port + "/api/game/" + updateId;

        HttpEntity<GameUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Game> all = gameRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(fixedName);
        assertThat(all.get(0).getDescription()).isEqualTo(fixedDescription);
        assertThat(all.get(0).getDeveloper()).isEqualTo(fixedDeveloper);
        assertThat(all.get(0).getPublisher()).isEqualTo(fixedPublisher);
        assertThat(all.get(0).getReleaseDate()).isEqualTo(fixedReleaseDate);
        assertThat(all.get(0).getHeaderImg()).isEqualTo(fixedHeaderImg);
        assertThat(all.get(0).getDownloadUrl()).isEqualTo(fixedDownloadUrl);
        assertThat(all.get(0).getRating()).isEqualTo(fixedRating);
    }
}
