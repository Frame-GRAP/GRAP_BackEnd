package com.grap.review.domain;

import com.grap.review.repository.ReviewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @AfterEach
    public void cleanup() {
        reviewRepository.deleteAll();
    }

    @Test
    public void 댓글저장_불러오기() {
        //given
        String content = "테스트 댓글";
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());

        reviewRepository.save(Review.builder()
                .content(content)
                .createdDate(createdDate)
                .build());

        //when
        List<Review> reviewList =  reviewRepository.findAll();

        //then
        Review review = reviewList.get(0);
        assertThat(review.getContent()).isEqualTo(content);
        assertThat(review.getCreatedDate()).isAfter(createdDate);
    }
}
