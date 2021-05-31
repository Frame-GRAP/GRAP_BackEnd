package com.grap.coupon.controller;

import com.grap.coupon.dto.CouponResponseDto;
import com.grap.coupon.dto.CouponSaveRequestDto;
import com.grap.coupon.dto.CouponUpdateRequestDto;
import com.grap.coupon.service.CouponService;
import com.grap.game.dto.GameResponseDto;
import com.grap.game.dto.GameUpdateRequestDto;
import com.grap.video.dto.VideoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class CouponApiController {

    private final CouponService couponService;

    @PostMapping("/api/game/{gameId}/coupon")
    public Long save(@RequestBody CouponSaveRequestDto requestDto, @PathVariable Long gameId) {
        return couponService.save(requestDto, gameId);
    }

    @GetMapping("/api/coupon/all")
    public List<CouponResponseDto> findAll() {
        return couponService.findAll();
    }

    @GetMapping("/api/game/{gameId}/coupon/all")
    public List<CouponResponseDto> findByGameId(@PathVariable Long gameId) {
        return couponService.findByGameId(gameId);
    }

    @DeleteMapping("/api/coupon/{couponId}")
    public Long delete(@PathVariable Long couponId) {
        return couponService.delete(couponId);
    }

    @PutMapping("/api/coupon/{couponId}")
    public Long update(@PathVariable Long couponId, @RequestBody CouponUpdateRequestDto requestDto){
        return couponService.update(couponId, requestDto);
    }



}
