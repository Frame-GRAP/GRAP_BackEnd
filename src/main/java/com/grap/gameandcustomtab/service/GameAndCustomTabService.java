package com.grap.gameandcustomtab.service;

import com.grap.customtab.domain.CustomTab;
import com.grap.customtab.repository.CustomTabRepository;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.gameandcustomtab.domain.GameAndCustomTab;
import com.grap.gameandcustomtab.dto.GameAndCustomTabResponseDto;
import com.grap.gameandcustomtab.repository.GameAndCustomTabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameAndCustomTabService {

    private final GameAndCustomTabRepository gameAndCustomTabRepository;
    private final GameRepository gameRepository;
    private final CustomTabRepository customTabRepository;

    @Transactional(readOnly = true)
    public List<GameAndCustomTabResponseDto> findAllGameAndCustomTab() {

        return gameAndCustomTabRepository.findAll().stream()
                .map(GameAndCustomTabResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long saveGameAndCustomTab(Long gameId, Long customTabId) {

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임은 존재하지 않습니다.")
        );

        CustomTab customTab = customTabRepository.findById(customTabId).orElseThrow(
                () -> new IllegalArgumentException("해당 탭은 존재하지 않습니다.")
        );

        GameAndCustomTab gameAndCustomTab = new GameAndCustomTab();
        gameAndCustomTab.mapGame(game);
        gameAndCustomTab.mapCustomTab(customTab);

        return gameAndCustomTabRepository.save(gameAndCustomTab).getId();
    }

    public Long deleteGameAndCustomTab(Long gameAndCustomTabId) {

        GameAndCustomTab gameAndCustomTab = gameAndCustomTabRepository.findById(gameAndCustomTabId).orElseThrow(
                () -> new IllegalArgumentException("해당 탭은 존재하지 않습니다.")
        );

        gameAndCustomTabRepository.delete(gameAndCustomTab);

        return gameAndCustomTabId;
    }
}
