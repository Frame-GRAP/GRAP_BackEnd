package com.grap.payment.service;

import com.grap.membership.domain.Membership;
import com.grap.membership.repository.MembershipRepository;
import com.grap.payment.domain.Payment;
import com.grap.payment.repository.PaymentRepository;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    @Transactional
    public void saveNextPayment(String customerUid, String merchantUid, BigDecimal amount) {

        Payment payment = new Payment(customerUid, merchantUid, amount);

        Long userId = Long.parseLong(customerUid.replaceAll("[^0-9]",""));
        Long membershipId = amount.intValue() == 4500 ? (long) 1: (long) 2;

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        Membership membership = membershipRepository.findById(membershipId).orElseThrow(
                () -> new IllegalArgumentException("해당 멤버십은 존재하지 않습니다.")
        );

        payment.mapUser(user);
        payment.mapMembership(membership);

        paymentRepository.save(payment);
    }

}
