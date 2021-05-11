package com.grap.game.controller;

import com.grap.game.service.GameService;
import com.grap.game.dto.GameResponseDto;
import com.grap.game.dto.GameSaveRequestDto;
import com.grap.game.dto.GameUpdateRequestDto;
import com.grap.video.util.VideoCrawling;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class GameApiController {
    private final GameService gameService;

    @PostMapping("/api/game")
    public Long save(@RequestBody GameSaveRequestDto requestDto) {
        return gameService.save(requestDto);
    }

    @PutMapping("/api/game/{gameId}")
    public Long update(@PathVariable Long gameId, @RequestBody GameUpdateRequestDto requestDto){
        return gameService.update(gameId, requestDto);
    }

    @GetMapping("/api/game/{gameId}")
    public GameResponseDto findById(@PathVariable Long gameId) {

        return gameService.findById(gameId);
    }

    @GetMapping("/api/game/all")
    public List<GameResponseDto> findAll() {

        return gameService.findAll();
    }
}

