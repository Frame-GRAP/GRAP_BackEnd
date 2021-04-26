package com.grap.backend.web.controller;

import com.grap.backend.service.game.GameService;
import com.grap.backend.web.dto.GameResponseDto;
import com.grap.backend.web.dto.GameSaveRequestDto;
import com.grap.backend.web.dto.GameUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class GameApiController {
    private final GameService gameService;

    @PostMapping("/api/game")
    public Long save(@RequestBody GameSaveRequestDto requestDto) {
        return gameService.save(requestDto);
    }

    @PutMapping("/api/game/{id}")
    public Long update(@PathVariable Long id, @RequestBody GameUpdateRequestDto requestDto){
        return gameService.update(id, requestDto);
    }

    @GetMapping("/api/game/{id}")
    public GameResponseDto findById(@PathVariable Long id) {

        return gameService.findById(id);
    }
}

