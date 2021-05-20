package com.grap.gameandcategory.controller;

import com.grap.gameandcategory.dto.GameAndCategoryListResponseDto;
import com.grap.gameandcategory.service.GameAndCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class GameAndCategoryApiController {
    private final GameAndCategoryService gameAndCategoryService;

    @PostMapping("/api/game/{gameId}/category/{categoryId}")
    public Long save(@PathVariable Long gameId, @PathVariable Long categoryId) {
        return gameAndCategoryService.save(gameId, categoryId);
    }
}

