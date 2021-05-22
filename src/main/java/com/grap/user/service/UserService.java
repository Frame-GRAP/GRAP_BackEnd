package com.grap.user.service;

import com.grap.user.domain.User;
import com.grap.user.dto.UserResponseDto;
import com.grap.user.dto.UserSaveRequestDto;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto saveOrUpdate(UserSaveRequestDto requestDto) {

        return userRepository.findByEmail(requestDto.getEmail())
                .map(user -> new UserResponseDto(user.update(requestDto.getName(), requestDto.getPicture()), true))
                .orElseGet(() -> new UserResponseDto(userRepository.save(requestDto.toEntity()), false));
    }

    @Transactional
    public Map<String, Boolean> dupCheckNickname(String nickname) {

        HashMap<String, Boolean> map = new HashMap<>();

        map.put("isDup", userRepository.findByNickname(nickname).isPresent());

        return map;
    }

    @Transactional
    public Long saveNickname(Long userId, String nickname) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        user.update(nickname);

        return userId;
    }
}