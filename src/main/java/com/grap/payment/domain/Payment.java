package com.grap.payment.domain;

import com.grap.domain.BaseTimeEntity;
import com.grap.membership.domain.Membership;
import com.grap.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
public class Payment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerUid;

    @Column(nullable = false)
    private String merchantUid;

    @Column(nullable = false)
    private BigDecimal paidAmount;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "membership_id")
    private Membership membership;

    public void mapMembership(Membership membership) {
        this.membership = membership;
    }

    public void mapUser(User user) {
        this.user = user;
    }

    @Builder
    public Payment(String customerUid, String merchantUid, BigDecimal paidAmount) {
        this.customerUid = customerUid;
        this.merchantUid = merchantUid;
        this.paidAmount = paidAmount;
    }

}
