package com.grap.starter.service;

import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.starter.domain.Starter;
import com.grap.starter.dto.StarterResponseDto;
import com.grap.starter.repository.StarterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StarterService {

    private final StarterRepository starterRepository;
    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<StarterResponseDto> findAllStarter() {

        return starterRepository.findAll().stream()
                .map(StarterResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long saveStarter(Long gameId) {

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임은 존재하지 않습니다.")
        );

        Starter starter = new Starter();

        starter.mapGame(game);

        return starterRepository.save(starter).getId();
    }

    public Long deleteStarter(Long starterId) {

        Starter starter = starterRepository.findById(starterId).orElseThrow(
                () -> new IllegalArgumentException("해당 스타터는 존재하지 않습니다.")
        );

        starterRepository.delete(starter);

        return starterId;
    }
}
