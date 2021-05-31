package com.grap.userandcoupon.controller;

import com.grap.coupon.dto.CouponResponseDto;
import com.grap.userandcoupon.dto.UserAndCouponResponseDto;
import com.grap.userandcoupon.service.UserAndCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserAndCouponController {

    private final UserAndCouponService userAndCouponService;

    @PostMapping("/api/user/{userId}/coupon/{couponId}/userAndCoupon")
    public Long save(@PathVariable Long userId, @PathVariable Long couponId) {
        return userAndCouponService.save(userId, couponId);
    }

    @GetMapping("/api/user/{userId}/coupon/userAndCoupon")
    public List<UserAndCouponResponseDto> findByUserId(@PathVariable Long userId) {
        return userAndCouponService.findByUserId(userId);
    }

    @DeleteMapping("/api/UserAndCoupon/{UserAndCouponId}")
    public Long deleteCustomTab(@PathVariable Long UserAndCouponId) {
        return userAndCouponService.delete(UserAndCouponId);
    }
}
