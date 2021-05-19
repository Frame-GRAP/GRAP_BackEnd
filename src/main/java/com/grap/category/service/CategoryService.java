package com.grap.category.service;

import com.grap.category.domain.Category;
import com.grap.category.dto.CategoryListResponseDto;
import com.grap.category.dto.CategorySaveRequestDto;
import com.grap.category.dto.CategoryUpdateRequestDto;
import com.grap.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryListResponseDto> findAllCategory() {
        return categoryRepository.findAll().stream()
                .map(CategoryListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long saveCategory(CategorySaveRequestDto requestDto) {

        Category category = requestDto.toEntity();

        return categoryRepository.save(category).getId();
    }

    @Transactional
    public Long updateCategory(Long categoryId, CategoryUpdateRequestDto requestDto) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.")
        );

        category.update(requestDto.getName());

        return categoryId;
    }

    @Transactional
    public void deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.")
        );

        categoryRepository.delete(category);
    }
}
