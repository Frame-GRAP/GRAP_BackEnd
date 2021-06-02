package com.grap.game.service;

import com.grap.category.repository.CategoryRepository;
import com.grap.game.domain.Game;
import com.grap.game.dto.GameResponseDto;
import com.grap.game.dto.GameSaveRequestDto;
import com.grap.game.dto.GameUpdateRequestDto;
import com.grap.game.repository.GameRepository;
import com.grap.gameandcategory.domain.GameAndCategory;
import com.grap.gameandcategory.repository.GameAndCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final GameAndCategoryRepository gameAndCategoryRepository;

    @Transactional
    public Long save(GameSaveRequestDto requestDto) {
        return gameRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, GameUpdateRequestDto requestDto){
        Game game = gameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게임이 없습니다. id = "+ id));

        game.update(requestDto.getName(), requestDto.getDescription(), requestDto.getDownloadUrl());

        return id;
    }

    @Transactional
    public GameResponseDto findById(Long id) {
        Game entity = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임이 없습니다. id=" + id));

        return new GameResponseDto(entity);
    }

    @Transactional
    public List<GameResponseDto> findRelatedGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게임이 없습니다. id=" + id));

        String relatedGameId = game.getRelatedGame().getRelatedGameId();
        String[] idList = relatedGameId.split(" ");

        List<Game> games = new ArrayList<>();
        for (String gameId : idList){
            games.add(gameRepository.findById(Long.parseLong(gameId))
                    .orElseThrow(() -> new IllegalArgumentException("해당 게임이 없습니다. id=" + gameId)));
        }

        return games.stream()
                .map(GameResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GameResponseDto> findAll() {
        return gameRepository.findAll().stream()
                .map(GameResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GameResponseDto> findByNameLike(String gameName){
        return gameRepository.findByNameLike("%" + gameName + "%").stream()
                .map(GameResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GameResponseDto> findByNameContaining(String gameName, int pageNum, int size) {
        PageRequest pageRequest = PageRequest.of(pageNum, size);

        Page<Game> games = gameRepository.findByNameContainingIgnoreCase(gameName, pageRequest);

        return games.stream().map(GameResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<GameResponseDto> findByCategoryId(Long gameId, Long categoryId, int size) {

        Page<GameAndCategory> gameAndCategories = fetchPages(gameId, categoryId, size);

        return gameAndCategories
                .stream()
                .map(GameAndCategory::getGame )
                .map(GameResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long delete(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("일치하는 게임이 없습니다. Id =" + gameId));

        gameRepository.deleteById(game.getId());
        return game.getId();
    }

    @Transactional(readOnly = true)
    public Long countAll() {

        return gameRepository.count();
    }

    public List<String> findAllTitles() {

        return gameRepository.findAll().stream()
                .map(Game::getName)
                .collect(Collectors.toList());
    }

    private Page<GameAndCategory> fetchPages(Long gameId, Long categoryId, int size) {
        PageRequest pageRequest = PageRequest.of(0, size);

        categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다. id : " + categoryId)
        );

        return gameAndCategoryRepository.findByCategoryIdAndGameIdLessThanOrderByGameIdDesc(categoryId, gameId, pageRequest);
    }
}