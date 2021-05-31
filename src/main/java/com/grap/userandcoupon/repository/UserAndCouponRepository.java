package com.grap.userandcoupon.repository;

import com.grap.userandcoupon.domain.UserAndCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAndCouponRepository extends JpaRepository<UserAndCoupon, Long> {
    List<UserAndCoupon> findByUserId(Long userId);
}
