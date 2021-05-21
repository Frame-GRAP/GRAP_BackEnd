package com.grap.starter.controller;

import com.grap.starter.dto.StarterResponseDto;
import com.grap.starter.service.StarterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class StarterApiController {

    private  final StarterService starterService;

    @GetMapping("/api/starter/all")
    public List<StarterResponseDto> findAllStarter() {
        return starterService.findAllStarter();
    }

    @PostMapping("/api/game/{gameId}/starter")
    public Long saveStarter(@PathVariable Long gameId) {
        return starterService.saveStarter(gameId);
    }

    @DeleteMapping("/api/starter/{starterId}")
    public Long deleteStarter(@PathVariable Long starterId) {
        return starterService.deleteStarter(starterId);
    }
}
