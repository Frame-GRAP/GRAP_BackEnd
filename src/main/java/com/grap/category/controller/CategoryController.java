package com.grap.category.controller;

import com.grap.category.dto.CategoryListResponseDto;
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
    public List<CategoryListResponseDto> findAllCategory() {
        return categoryService.findAllCategory();
    }

    @PostMapping("/api/category")
    public Long saveCategory(@RequestBody CategorySaveRequestDto requestDto) {
        return categoryService.saveCategory(requestDto);
    }

    @PutMapping("/api/category/{categoryId}")
    public Long updateCategory(@PathVariable Long categoryId, @RequestBody CategoryUpdateRequestDto requestDto) {
        return categoryService.updateCategory(categoryId, requestDto);
    }

    @DeleteMapping("/api/category/{categoryId}")
    public Long deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);

        return categoryId;
    }




}
