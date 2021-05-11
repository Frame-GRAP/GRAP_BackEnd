package com.grap.favor.service;

import com.grap.favor.domain.Favor;
import com.grap.favor.dto.FavorListResponseDto;
import com.grap.favor.repository.FavorRepository;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.user.config.auth.dto.SessionUser;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import com.grap.video.domain.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FavorService {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final FavorRepository favorRepository;

    @Transactional
    public Long save(Long userId, Long gameId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id : " + userId));

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임은 존재하지 않습니다."));

        Favor favor = new Favor();
        favor.mapUser(user);
        favor.mapGame(game);

        return favorRepository.save(favor).getId();
    }

    @Transactional
    public List<FavorListResponseDto> findFavorByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id : " + userId));

        return user.getFavors().stream()
                .map(FavorListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long delete(Long userId, Long gameId) {
        Favor favor = favorRepository.findByUserIdAndGameId(userId, gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 정보와 일치하는 찜이 없습니다. userId = " + userId + "gameId = " + gameId));

        favorRepository.deleteById(favor.getId());
        return favor.getId();
    }
}
