package com.grap.user.controller;

import com.grap.user.dto.UserInfoResponseDto;
import com.grap.user.dto.UserResponseDto;
import com.grap.user.dto.UserSaveRequestDto;
import com.grap.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("api/user/{userId}")
    public UserInfoResponseDto findByUserId(@PathVariable Long userId) {
        return userService.findByUserId(userId);
    }

    @PostMapping("api/user/{userId}/nickname/{nickname}")
    public Long saveNickname(@PathVariable Long userId, @PathVariable String nickname) {
        return userService.saveNickname(userId, nickname);
    }

    @GetMapping("/api/user/all")
    public List<UserInfoResponseDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/api/user/countAll")
    public Long countAll() {

        return userService.countAll();
    }

    @GetMapping("/api/user/nameAll")
    public List<String> findAllNames() {

        return userService.findAllNames();
    }

    @DeleteMapping("/api/user/{userId}")
    public Long delete(@PathVariable Long userId){
        return userService.delete(userId);
    }


    @PutMapping("api/user/{userId}/membership/{membershipId}")
    public String userRegisterMembership(@PathVariable Long userId, @PathVariable Long membershipId) {
        return userService.mapMembership(userId, membershipId);
    }

    @PutMapping("/api/user/{userId}/membership")
    public String unsubscribeMembership(@PathVariable Long userId) {
        return userService.unsubscribeMembership(userId);
    }

}