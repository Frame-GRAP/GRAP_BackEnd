package com.grap.userandcoupon.service;

import com.grap.coupon.domain.Coupon;
import com.grap.coupon.repository.CouponRepository;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import com.grap.userandcoupon.domain.UserAndCoupon;
import com.grap.userandcoupon.dto.UserAndCouponResponseDto;
import com.grap.userandcoupon.repository.UserAndCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserAndCouponService {

    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final UserAndCouponRepository userAndCouponRepository;

    @Transactional
    public Long save(Long userId, Long couponId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );

        Coupon coupon = couponRepository.findById(couponId).orElseThrow(
                () -> new IllegalArgumentException("해당 쿠폰은 존재하지 않습니다.")
        );

        if(user.getAvailableCoupon() == 0)
            return (long) -1;

        UserAndCoupon userAndCoupon = new UserAndCoupon();

        userAndCoupon.setCode(createCouponCode(8));

        userAndCoupon.mapUser(user);
        userAndCoupon.mapCoupon(coupon);

        user.updateAvailableCoupon(user.getAvailableCoupon() - 1);

        return userAndCouponRepository.save(userAndCoupon).getId();
    }

    @Transactional
    public List<UserAndCouponResponseDto> findByUserId(Long userId) {
        return userAndCouponRepository.findByUserId(userId).stream()
                .map(UserAndCouponResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long delete(Long userAndCouponId) {

        UserAndCoupon userAndCoupon = userAndCouponRepository.findById(userAndCouponId).orElseThrow(
                () -> new IllegalArgumentException("유저 또는 쿠폰 아이디가 잘못되었습니다.")
        );

        userAndCouponRepository.delete(userAndCoupon);

        return userAndCouponId;
    }

    public String createCouponCode(int codeLength){
        final char[] possibleCharacters =
                {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F',
                        'G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V',
                        'W','X','Y','Z'};

        final int possibleCharacterCount = possibleCharacters.length;
        Random rnd = new Random();
        StringBuffer buf = new StringBuffer(16);

        for (int i= 0; i < codeLength; i++) {
            buf.append(possibleCharacters[rnd.nextInt(possibleCharacterCount)]);
        }

        return buf.toString();
    }
}
