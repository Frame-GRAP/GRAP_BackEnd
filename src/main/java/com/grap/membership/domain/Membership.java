package com.grap.membership.domain;

import com.grap.payment.domain.Payment;
import com.grap.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer couponNum;

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @Builder
    public Membership(String name, Integer price, Integer couponNum) {
        this.name = name;
        this.price = price;
        this.couponNum = couponNum;
    }

    public void update(String name, Integer price, Integer couponNum) {
        this.name = name;
        this.price = price;
        this.couponNum = couponNum;
    }
}
