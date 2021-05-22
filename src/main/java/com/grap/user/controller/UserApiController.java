package com.grap.user.controller;

import com.grap.user.dto.UserResponseDto;
import com.grap.user.dto.UserSaveRequestDto;
import com.grap.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/user")
    public UserResponseDto saveOrUpdate(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.saveOrUpdate(userSaveRequestDto);
    }

    @GetMapping("api/user/{userId}/nickname/{nickname}")
    public Map<String, Boolean> dupCheckNickname(@PathVariable String nickname) {
        return userService.dupCheckNickname(nickname);
    }

    @PostMapping("api/user/{userId}/nickname/{nickname}")
    public Long saveNickname(@PathVariable Long userId, @PathVariable String nickname) {
        return userService.saveNickname(userId, nickname);
    }

}