package com.grap.user.controller;

import com.grap.user.dto.UserSaveRequestDto;
import com.grap.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/user")
    public Long saveOrUpdate(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.saveOrUpdate(userSaveRequestDto);
    }
}