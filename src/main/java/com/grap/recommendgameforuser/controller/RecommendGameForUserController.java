package com.grap.recommendgameforuser.controller;

import com.grap.recommendgameforuser.dto.RecommendGameForUserResponseDto;
import com.grap.recommendgameforuser.service.RecommendGameForUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class RecommendGameForUserController {
    private final RecommendGameForUserService recommendGameForUserService;

    @PostMapping("/api/user/{userId}/game/{gameId}/RecommendGameForUser")
    public Long save(@PathVariable Long userId, @PathVariable Long gameId) {
        return recommendGameForUserService.save(userId, gameId);
    }

    @GetMapping("/api/user/{userId}/RecommendGameForUser")
    public List<RecommendGameForUserResponseDto> findByUserId(@PathVariable Long userId) {
        return recommendGameForUserService.findByUserId(userId);
    }
}
