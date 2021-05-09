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

    @PostMapping("/api/favor/{gameId}")
    public Long save(@LoginUser SessionUser user, @PathVariable Long gameId) {
        return favorService.save(user, gameId);
    }

    @GetMapping("/api/favor/all")
    public List<FavorListResponseDto> findFavorByUser(@LoginUser SessionUser user) {
        return favorService.findFavorByUser(user);
    }

    @DeleteMapping("/api/favor/{gameId}")
    public Long delete (@LoginUser SessionUser user, @PathVariable Long gameId) {
        return favorService.delete(user, gameId);
    }
}
