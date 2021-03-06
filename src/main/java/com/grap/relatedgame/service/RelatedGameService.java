package com.grap.relatedgame.service;

import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.relatedgame.domain.RelatedGame;
import com.grap.relatedgame.dto.RelatedGameResponseDto;
import com.grap.relatedgame.repository.RelatedGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RelatedGameService {

    private final GameRepository gameRepository;
    private final RelatedGameRepository relatedGameRepository;

    @Transactional
    public Long save(Long gameId, String relatedGameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("일치하는 게임이 없습니다. Id =" + gameId));

        RelatedGame relatedGame = RelatedGame.builder()
                .relatedGameId(relatedGameId)
                .build();

        relatedGame.mapGame(game);

        return relatedGameRepository.save(relatedGame).getId();
    }

    @Transactional
    public RelatedGameResponseDto findByGameId(Long gameId) {

        RelatedGame relatedGame = relatedGameRepository.findByGameId(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임에 연관된 게임이 존재하지 않습니다.")
        );

        return new RelatedGameResponseDto(relatedGame);
    }
}
