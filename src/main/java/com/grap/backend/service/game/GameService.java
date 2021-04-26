package com.grap.backend.service.game;

import com.grap.backend.domain.game.Game;
import com.grap.backend.domain.game.GameRepository;
import com.grap.backend.web.dto.GameResponseDto;
import com.grap.backend.web.dto.GameSaveRequestDto;
import com.grap.backend.web.dto.GameUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public GameResponseDto findById(Long id) {
        Game entity = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new GameResponseDto(entity);
    }

}