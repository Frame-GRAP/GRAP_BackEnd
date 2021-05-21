package com.grap.gameandcustomtab.controller;

import com.grap.gameandcustomtab.dto.GameAndCustomTabResponseDto;
import com.grap.gameandcustomtab.service.GameAndCustomTabService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class GameAndCustomTabApiController {

    private final GameAndCustomTabService gameAndCustomTabService;

    @GetMapping("/api/gameAndCustomTab/all")
    public List<GameAndCustomTabResponseDto> findAllGameAndCustomTab() {
        return gameAndCustomTabService.findAllGameAndCustomTab();
    }

    @PostMapping("/api/game/{gameId}/customTab/{customTabId}")
    public Long save(@PathVariable Long gameId, @PathVariable Long customTabId) {
        return gameAndCustomTabService.saveGameAndCustomTab(gameId, customTabId);
    }

    @DeleteMapping("/api/gameAndCustomTab/{gameAndCustomTabId}")
    public Long deleteCustomTab(@PathVariable Long gameAndCustomTabId) {
        return gameAndCustomTabService.deleteGameAndCustomTab(gameAndCustomTabId);
    }

}
