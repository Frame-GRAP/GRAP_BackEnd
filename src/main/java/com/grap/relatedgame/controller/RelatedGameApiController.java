package com.grap.relatedgame.controller;

import com.grap.relatedgame.dto.RelatedGameResponseDto;
import com.grap.relatedgame.service.RelatedGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class RelatedGameApiController {

    private final RelatedGameService relatedGameService;

    @GetMapping("/api/game/{gameId}/relatedGame")
    public RelatedGameResponseDto findAllRelatedGame(@PathVariable Long gameId) {

        return relatedGameService.findByGameId(gameId);
    }
}
