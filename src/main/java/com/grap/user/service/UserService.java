package com.grap.user.service;

import com.grap.user.dto.UserResponseDto;
import com.grap.user.dto.UserSaveRequestDto;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveOrUpdate(UserSaveRequestDto requestDto) {

        return userRepository.findByEmail(requestDto.getEmail())
                .map(user -> new UserResponseDto(user.update(requestDto.getName(), requestDto.getPicture()), true))
                .orElseGet(() -> new UserResponseDto(userRepository.save(requestDto.toEntity()), false));
    }

    public Map<String, Boolean> saveNickname(String nickname) {

        HashMap<String, Boolean> map = new HashMap<>();

        map.put("isDup", userRepository.findByNickname(nickname).isPresent());

        return map;
    }
}