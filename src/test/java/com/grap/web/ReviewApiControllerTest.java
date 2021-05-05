package com.grap.web;

import com.grap.review.domain.Review;
import com.grap.review.dto.ReviewSaveRequestDto;
import com.grap.review.repository.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReviewApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReviewRepository reviewRepository;

    @AfterEach
    public void tearDown() throws Exception {
        reviewRepository.deleteAll();
    }

    @Test
    public void Review_등록된다() throws Exception {
        //given
        String content = "content";
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        ReviewSaveRequestDto requestDto = ReviewSaveRequestDto.builder()
                .content(content)
                .createdDate(createdDate)
                .build();

        String url = "http://localhost:" + port + "/api/review";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Review> all = reviewRepository.findAll();
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getCreatedDate()).isAfter(createdDate);
    }
}
