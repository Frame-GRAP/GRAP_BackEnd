package com.grap.user.service;

import com.grap.user.config.auth.dto.SessionUser;
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

    public Long save(UserSaveRequestDto userSaveRequestDto){
        User user = userSaveRequestDto.toEntity();
        httpSession.setAttribute("user", new SessionUser(user));


        return userRepository.save(user).getId();
    }
}
