package com.grap.usergamepreference.controller;

import com.grap.usergamepreference.dto.UserGamePreferenceResponseDto;
import com.grap.usergamepreference.dto.UserGamePreferenceSaveRequestDto;
import com.grap.usergamepreference.service.UserGamePreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserGamePreferenceApiController {

    private final UserGamePreferenceService userGamePreferenceService;

    @GetMapping("/api/user/{userId}/userGamePreference")
    public UserGamePreferenceResponseDto findByUserIdAndGameId(@PathVariable Long userId) {

        return userGamePreferenceService.findByUserId(userId);
    }

    @PostMapping("/api/user/{userId}/userGamePreference")
    public Long saveUserGamePreferences(@PathVariable Long userId, @RequestBody Map<String, List<UserGamePreferenceSaveRequestDto>> mappedRequestDtos) {

        String key = mappedRequestDtos.keySet().iterator().next();
        List<UserGamePreferenceSaveRequestDto> requestDtos = mappedRequestDtos.get(key);

        return userGamePreferenceService.saveUserGamePreferences(userId, requestDtos);
    }
}
