package com.grap.category.service;

import com.grap.category.domain.Category;
import com.grap.category.dto.CategoryResponseDto;
import com.grap.category.dto.CategorySaveRequestDto;
import com.grap.category.dto.CategoryUpdateRequestDto;
import com.grap.category.repository.CategoryRepository;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.gameandcategory.domain.GameAndCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> findAllCategory() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CategoryResponseDto> findByGame(Long gameId){
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임이 존재하지 않습니다. id : " + gameId));

        List<Category> categories = new ArrayList<>();
        for (GameAndCategory gameAndCategory : game.getGameAndCategory()){
            categories.add(gameAndCategory.getCategory());
        }

        return categories.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long save(CategorySaveRequestDto requestDto) {

        Category category = requestDto.toEntity();

        return categoryRepository.save(category).getId();
    }

    @Transactional
    public Long update(Long categoryId, CategoryUpdateRequestDto requestDto) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.")
        );

        category.update(requestDto.getName(), requestDto.getUiName());

        return categoryId;
    }

    @Transactional
    public void delete(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.")
        );

        categoryRepository.delete(category);
    }
}
