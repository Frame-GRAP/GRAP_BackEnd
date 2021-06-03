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
    public int savePayment(String customerUid, String merchantUid, BigDecimal amount) {

        Long userId = Long.parseLong(customerUid.replaceAll("[^0-9]",""));

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        Membership membership = membershipRepository.findByPrice(amount.intValue()).orElseThrow(
                () -> new IllegalArgumentException("해당 멤버십은 존재하지 않습니다.")
        );

        if(!user.isOnSubscription()) {
            user.unmapMembership(membership);
            return -1;
        }

        Payment payment = new Payment(customerUid, merchantUid, amount);

        payment.mapUser(user);
        payment.mapMembership(membership);

        return paymentRepository.save(payment).getId().intValue();
    }

}
