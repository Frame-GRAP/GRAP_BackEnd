package com.grap.user.service;

import com.grap.user.domain.User;
import com.grap.user.dto.UserSaveRequestDto;
import com.grap.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public Long saveOrUpdate(UserSaveRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .map(entity -> entity.update(requestDto.getName(), requestDto.getPicture()))
                .orElse(requestDto.toEntity());

        return userRepository.save(user).getId();
    }
}