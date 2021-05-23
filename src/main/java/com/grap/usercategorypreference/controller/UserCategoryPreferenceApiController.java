package com.grap.usercategorypreference.controller;

import com.grap.usercategorypreference.dto.UserCategoryPreferenceResponseDto;
import com.grap.usercategorypreference.service.UserCategoryPreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class UserCategoryPreferenceApiController {

    private final UserCategoryPreferenceService userCategoryPreferenceService;

    @GetMapping("/api/user/{userId}/userCategoryPreference/all")
    public List<UserCategoryPreferenceResponseDto> findByUserId(@PathVariable Long userId) {

        return userCategoryPreferenceService.findCategoryPreferenceByUser(userId);
    }
}
