package com.grap.user.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.report.domain.Report;
import com.grap.favor.domain.Favor;
import com.grap.review.domain.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Review> userReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Report> userReports = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Favor> favors = new ArrayList<>();

    @Builder
    public User(String email, String name, String picture, Role role) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }
}