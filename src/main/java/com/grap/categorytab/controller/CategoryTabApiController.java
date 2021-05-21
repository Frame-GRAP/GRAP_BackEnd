package com.grap.categorytab.controller;

import com.grap.categorytab.dto.CategoryTabResponseDto;
import com.grap.categorytab.service.CategoryTabService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class CategoryTabApiController {

    private final CategoryTabService categoryTabService;

    @GetMapping("/api/categoryTab/all")
    public List<CategoryTabResponseDto> findAllCategoryTab() {
        return categoryTabService.findAllCategoryTab();
    }

    @PostMapping("/api/game/{gameId}/category/{categoryId}/categoryTab")
    public Long saveCustomTab(@PathVariable Long gameId, @PathVariable Long categoryId) {
        return categoryTabService.saveCategoryTab(gameId, categoryId);
    }

    @DeleteMapping("/api/categoryTab/{categoryTabId}")
    public Long deleteCustomTab(@PathVariable Long categoryTabId) {
        return categoryTabService.deleteCategoryTab(categoryTabId);
    }
}
