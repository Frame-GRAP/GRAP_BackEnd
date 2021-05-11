package com.grap.favor.controller;

import com.grap.favor.dto.FavorListResponseDto;
import com.grap.favor.service.FavorService;
import com.grap.user.config.auth.LoginUser;
import com.grap.user.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class FavorApiController {
    private final FavorService favorService;

    @PostMapping("/api/user/{userId}/game/{gameId}/favor")
    public Long save(@PathVariable Long userId, @PathVariable Long gameId) {
        return favorService.save(userId, gameId);
    }

    @GetMapping("/api/user/{userId}/favor/all")
    public List<FavorListResponseDto> findFavorByUser(@PathVariable Long userId) {
        return favorService.findFavorByUser(userId);
    }

    @DeleteMapping("/api/user/{userId}/game/{gameId}/favor")
    public Long delete (@PathVariable Long userId, @PathVariable Long gameId) {
        return favorService.delete(userId, gameId);
    }
}
