package com.grap.categorytab.service;

import com.grap.category.domain.Category;
import com.grap.category.repository.CategoryRepository;
import com.grap.categorytab.domain.CategoryTab;
import com.grap.categorytab.dto.CategoryTabGameResponseDto;
import com.grap.categorytab.dto.CategoryTabResponseDto;
import com.grap.categorytab.repository.CategoryTabRepository;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryTabService {

    private final CategoryTabRepository categoryTabRepository;
    private final CategoryRepository categoryRepository;
    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<CategoryTabResponseDto> findAllCategoryTab() {

        return categoryTabRepository.findAll().stream()
                .map(CategoryTabResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long saveCategoryTab(Long gameId, Long categoryId) {

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임은 존재하지 않습니다.")
        );

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다. category Id =" + categoryId.toString())
        );

        CategoryTab categoryTab = new CategoryTab();

        categoryTab.mapGame(game);
        categoryTab.mapCategory(category);

        return categoryTabRepository.save(categoryTab).getId();
    }

    public Long deleteCategoryTab(Long categoryTabId) {

        CategoryTab categoryTab = categoryTabRepository.findById(categoryTabId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.")
        );

        categoryTabRepository.delete(categoryTab);

        return categoryTabId;
    }

    @Transactional(readOnly = true)
    public List<CategoryTabGameResponseDto> findByCategoryId(Long categoryId) {

        return categoryTabRepository.findByCategoryId(categoryId).stream()
                .map(CategoryTabGameResponseDto::new)
                .collect(Collectors.toList());
    }
}
