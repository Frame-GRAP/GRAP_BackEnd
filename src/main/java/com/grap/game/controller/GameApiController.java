package com.grap.game.controller;

import com.grap.game.service.GameService;
import com.grap.game.dto.GameResponseDto;
import com.grap.game.dto.GameSaveRequestDto;
import com.grap.game.dto.GameUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/api/game/all")
    public List<GameResponseDto> findAllDesc() {

        return gameService.findAllDesc();
    }
}
