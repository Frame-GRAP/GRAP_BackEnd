package com.grap.usergamepreference.controller;

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

//    @GetMapping("/api/user/{userId}/userGamePreference/all")
//    public List<UserGamePreferenceResponseDto> findByUserId(Long userId) {
//        return userGamePreferenceService.findByUserId(userId);
//    }
    @PostMapping("/api/user/{userId}/userGamePreference")
    public Long saveUserGamePreferences(@PathVariable Long userId, @RequestBody Map<String, List<UserGamePreferenceSaveRequestDto>> mappedRequestDtos) {

        //List<UserGamePreferenceSaveRequestDto> theList = new ArrayList((Collection) requestDtos);

        String key = mappedRequestDtos.keySet().iterator().next();
        List<UserGamePreferenceSaveRequestDto> requestDtos = mappedRequestDtos.get(key);

        return userGamePreferenceService.saveUserGamePreferences(userId, requestDtos);
    }
}
