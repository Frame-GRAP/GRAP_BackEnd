package com.grap.coupon.service;

import com.grap.coupon.domain.Coupon;
import com.grap.coupon.dto.CouponResponseDto;
import com.grap.coupon.dto.CouponSaveRequestDto;
import com.grap.coupon.dto.CouponUpdateRequestDto;
import com.grap.coupon.repository.CouponRepository;
import com.grap.game.domain.Game;
import com.grap.game.dto.GameUpdateRequestDto;
import com.grap.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final GameRepository gameRepository;
    private final CouponRepository couponRepository;

    @Transactional
    public Long save(CouponSaveRequestDto requestDto, Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("아이디에 해당하는 게임이 없습니다. Id =" + gameId));

        Coupon coupon = requestDto.toEntity();
        coupon.mapGame(game);

        return couponRepository.save(coupon).getId();
    }

    @Transactional
    public List<CouponResponseDto> findByGameId(Long gameId) {
        return couponRepository.findByGameId(gameId).stream()
                .map(CouponResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CouponResponseDto> findAll() {
        return couponRepository.findAll().stream()
                .map(CouponResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, CouponUpdateRequestDto requestDto){
        Coupon coupon = couponRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 쿠폰이 없습니다. id = "+ id));

        coupon.update(requestDto.getName(), requestDto.getExpirationDate());

        return id;
    }


    @Transactional
    public Long delete(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(
                () -> new IllegalArgumentException("아이디에 해당하는 쿠폰이 없습니다. Id =" + couponId));

        couponRepository.deleteById(coupon.getId());
        return coupon.getId();
    }
}
