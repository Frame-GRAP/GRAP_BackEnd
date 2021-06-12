package com.grap.recommendgameforuser.service;

import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.recommendgameforuser.domain.RecommendGameForUser;
import com.grap.recommendgameforuser.dto.RecommendGameForUserResponseDto;
import com.grap.recommendgameforuser.repository.RecommendGameForUserRepository;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendGameForUserService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final RecommendGameForUserRepository recommendGameForUserRepository;

    @Transactional
    public Long save(Long userId, Long gameId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id : " + userId));

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임은 존재하지 않습니다."));

        RecommendGameForUser recommendGameForUser = new RecommendGameForUser();

        recommendGameForUser.mapGame(game);
        recommendGameForUser.mapUser(user);

        return recommendGameForUserRepository.save(recommendGameForUser).getId();
    }

    @Transactional
    public List<RecommendGameForUserResponseDto> findByUserId(Long userId) {
        return recommendGameForUserRepository.findByUserId(userId).stream()
                .map(RecommendGameForUserResponseDto::new)
                .collect(Collectors.toList());
    }
}
