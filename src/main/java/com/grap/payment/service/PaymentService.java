package com.grap.payment.service;

import com.grap.membership.domain.Membership;
import com.grap.membership.repository.MembershipRepository;
import com.grap.payment.domain.Payment;
import com.grap.payment.dto.PaymentSaveRequestDto;
import com.grap.payment.repository.PaymentRepository;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;

    @Transactional
    public Long savePayment(PaymentSaveRequestDto requestDto) {

        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        Membership membership = membershipRepository.findById(requestDto.getMembershipId()).orElseThrow(
                () -> new IllegalArgumentException("해당 멤버십은 존재하지 않습니다.")
        );

        Payment payment = requestDto.toEntity();
        payment.mapMembership(membership);
        payment.mapUser(user);

        return paymentRepository.save(payment).getId();
    }

    @Transactional
    public void saveNextPayment(String customerUid, String merchantUid) {

        Payment existingPayment = paymentRepository.findFirstByCustomerUidOrderByIdDesc(customerUid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 결제내역 입니다")
        );

        Payment payment = new Payment(customerUid, merchantUid, existingPayment.getPaidAmount());
        payment.mapUser(existingPayment.getUser());
        payment.mapMembership(existingPayment.getMembership());

        paymentRepository.save(payment);
    }

}
