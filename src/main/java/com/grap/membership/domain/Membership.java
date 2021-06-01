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

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<User> userMembership = new ArrayList<>();

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    @Builder
    public Membership(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public void update(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
