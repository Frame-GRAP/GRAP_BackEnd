package com.grap.gameandcategory.service;

import com.grap.category.domain.Category;
import com.grap.category.repository.CategoryRepository;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.gameandcategory.domain.GameAndCategory;
import com.grap.gameandcategory.dto.GameAndCategoryListResponseDto;
import com.grap.gameandcategory.repository.GameAndCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameAndCategoryService {

    private final GameAndCategoryRepository gameAndCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final GameRepository gameRepository;

    @Transactional
    public Long save(Long gameId, Long categoryId) {

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임은 존재하지 않습니다.")
        );

        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.")
        );

        GameAndCategory gameAndCategory = new GameAndCategory();
        gameAndCategory.mapCategory(category);
        gameAndCategory.mapGame(game);

        return gameAndCategoryRepository.save(gameAndCategory).getId();
    }
}
