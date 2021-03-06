package com.grap.user.service;

import com.grap.membership.domain.Membership;
import com.grap.membership.repository.MembershipRepository;
import com.grap.payment.domain.Payment;
import com.grap.payment.repository.PaymentRepository;
import com.grap.user.domain.User;
import com.grap.user.dto.UserInfoResponseDto;
import com.grap.user.dto.UserResponseDto;
import com.grap.user.dto.UserSaveRequestDto;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final MembershipRepository membershipRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public UserResponseDto saveOrUpdate(UserSaveRequestDto requestDto) {

        return userRepository.findByEmail(requestDto.getEmail())
                .map(user -> new UserResponseDto(user.update(requestDto.getName(), requestDto.getPicture()), true))
                .orElseGet(() -> new UserResponseDto(userRepository.save(requestDto.toEntity()), false));
    }

    @Transactional
    public Map<String, Boolean> dupCheckNickname(String nickname) {

        HashMap<String, Boolean> map = new HashMap<>();

        map.put("isDup", userRepository.findByNickname(nickname).isPresent());

        return map;
    }

    @Transactional
    public Long saveNickname(Long userId, String nickname) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        user.update(nickname);

        return userId;
    }

    @Transactional(readOnly = true)
    public Long countAll() {

        return userRepository.count();
    }

    @Transactional(readOnly = true)
    public List<UserInfoResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserInfoResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<String> findAllNames() {

        return userRepository.findAll().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long delete(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        List<Payment> payments = paymentRepository.findAllByUserId(userId);

        for(Payment p : payments)
            paymentRepository.delete(p);

        userRepository.delete(user);

        return user.getId();
    }

    @Transactional(readOnly = true)
    public UserInfoResponseDto findByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        return new UserInfoResponseDto(user);
    }

    @Transactional
    public String mapMembership(Long userId, Long membershipId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        Membership membership = membershipRepository.findById(membershipId).orElseThrow(
                () -> new IllegalArgumentException("해당 멤버십은 존재하지 않습니다.")
        );

        user.mapMembership(membership);
        user.updateOnSubscription(true);
        user.updateAvailableCoupon(membership.getCouponNum());

        return "멤버십 구독 완료";
    }

    @Transactional
    public String unsubscribeMembership(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        user.updateOnSubscription(false);
        user.updateNextPaymentDay(null);

        return "구독 해지 완료";
    }

    @Transactional
    public void updateNextPaymentDay(long userId, Date date) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        user.updateNextPaymentDay(date);
    }
}