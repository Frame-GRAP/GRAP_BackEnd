package com.grap.userandcoupon.domain;

import com.grap.coupon.domain.Coupon;
import com.grap.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserAndCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String code;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setCode(String code){
        this.code = code;
    }

    public void mapCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public void mapUser(User user) {
        this.user = user;
    }
}
