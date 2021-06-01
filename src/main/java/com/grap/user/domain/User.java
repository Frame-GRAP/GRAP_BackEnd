package com.grap.user.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.favor.domain.Favor;
import com.grap.membership.domain.Membership;
import com.grap.payment.domain.Payment;
import com.grap.report.domain.Report;
import com.grap.review.domain.Review;
import com.grap.starter.domain.Starter;
import com.grap.userandcoupon.domain.UserAndCoupon;
import com.grap.usercategorypreference.domain.UserCategoryPreference;
import com.grap.usergamepreference.domain.UserGamePreference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> userReviews = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Report> userReports = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favor> favors = new ArrayList<>();

    @OneToOne(mappedBy = "game", cascade = CascadeType.REMOVE)
    private Starter starter;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserGamePreference> userGamePreferences = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserCategoryPreference> userCategoryPreferences = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserAndCoupon> userAndCoupons = new ArrayList<>();

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<Payment> pays = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Membership membership;

    @Builder
    public User(String email, String name, String picture, String nickname, Role role) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.nickname = nickname;
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

    public void update(String nickname) {
        this.nickname = nickname;
    }

    public void mapMembership(Membership membership) {
        this.membership = membership;
    }
}