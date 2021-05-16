package com.grap.reviewvalue.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.review.domain.Review;
import com.grap.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ReviewValue extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //0 for dislike, 1 for like
    private Boolean value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    public void mapUser(User user) {
        this.user = user;
    }

    public void mapReview(Review review) {
        this.review = review;
    }

    @Builder
    public ReviewValue(Boolean value, User user, Review review) {
        this.value = value;
        this.user = user;
        this.review = review;
    }

    public void update(Boolean value) {
        this.value = value;
    }
}
