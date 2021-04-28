package com.grap.game.service;

import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.game.dto.GameResponseDto;
import com.grap.game.dto.GameSaveRequestDto;
import com.grap.game.dto.GameUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GameService {
    private final GameRepository gameRepository;

    @Transactional
    public Long save(GameSaveRequestDto requestDto) {
        return gameRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, GameUpdateRequestDto requestDto){
        Game game = gameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게임이 없습니다. id = "+ id));

        game.update(requestDto.getName(), requestDto.getDescription(), requestDto.getDeveloper(), requestDto.getPublisher(), requestDto.getReleaseDate(), requestDto.getHeaderImg(), requestDto.getDownloadUrl(), requestDto.getRating());

        return id;
    }

    @Transactional
    public GameResponseDto findById(Long id) {
        Game entity = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new GameResponseDto(entity);
    }

    @Transactional
    public List<GameResponseDto> findAllDesc() {
        return gameRepository.findAllDesc().stream()
                .map(GameResponseDto::new)
                .collect(Collectors.toList());
    }

}