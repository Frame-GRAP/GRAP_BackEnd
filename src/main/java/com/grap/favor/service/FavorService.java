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
    public Long save(SessionUser sessionUser, Long gameId) {
        //        유저 주입 테스트용
        Optional<User> opUser = userRepository.findById(Long.valueOf(1));
        User user1 = opUser.get();
        sessionUser = new SessionUser(user1);

        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 게임은 존재하지 않습니다.")
        );

        Favor favor = new Favor();
        favor.mapUser(user);
        favor.mapGame(game);

        return favorRepository.save(favor).getId();
    }

    @Transactional
    public List<FavorListResponseDto> findFavorByUser(SessionUser sessionUser) {
        //        유저 주입 테스트용 (아이디 추가 시 안의 value를 sessionUser.getId()로 변경할 것)
        Optional<User> opUser = userRepository.findById(Long.valueOf(1));
        User user = opUser.get();

        return user.getFavors().stream()
                .map(FavorListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long delete(SessionUser sessionUser, Long gameId) {
        // 테스트를 위해 userId를 1로 대체. 추후 변경할 것
        Optional<Favor> opFavor = favorRepository.findByUserIdAndGameId(Long.valueOf(1l), gameId);

        if(opFavor.isEmpty()) {
            throw new IllegalArgumentException("영상과 일치하는 게임이 없습니다. Id =" + gameId);
        }

        Favor favor = opFavor.get();
        favorRepository.deleteById(favor.getId());
        return favor.getId();
    }
}
