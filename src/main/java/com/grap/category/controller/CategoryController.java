package com.grap.category.controller;

import com.grap.category.dto.CategoryResponseDto;
import com.grap.category.dto.CategorySaveRequestDto;
import com.grap.category.dto.CategoryUpdateRequestDto;
import com.grap.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/category/all")
    public List<CategoryResponseDto> findAllCategory() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/api/game/{gameID}/category/all")
    public List<CategoryResponseDto> findByGame(@PathVariable Long gameID){
        return categoryService.findByGame(gameID);
    }

    @PostMapping("/api/category")
    public Long saveCategory(@RequestBody CategorySaveRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @PutMapping("/api/category/{categoryId}")
    public Long updateCategory(@PathVariable Long categoryId, @RequestBody CategoryUpdateRequestDto requestDto) {
        return categoryService.update(categoryId, requestDto);
    }

    @DeleteMapping("/api/category/{categoryId}")
    public Long deleteCategory(@PathVariable Long categoryId) {
        categoryService.delete(categoryId);

        return categoryId;
    }




}