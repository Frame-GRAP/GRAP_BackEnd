package com.grap.customtab.controller;

import com.grap.customtab.dto.CustomTabResponseDto;
import com.grap.customtab.dto.CustomTabSaveRequestDto;
import com.grap.customtab.service.CustomTabService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class CustomTabApiController {

    private final CustomTabService customTabService;

    @GetMapping("/api/customTab/all")
    public List<CustomTabResponseDto> findAllCustomTab() {
        return customTabService.findAllCustomTab();
    }

    @PostMapping("/api/customTab")
    public Long saveCustomTab(@RequestBody CustomTabSaveRequestDto requestDto) {
        return customTabService.saveCustomTab(requestDto);
    }

    @DeleteMapping("/api/customTab/{customTabId}")
    public Long deleteCustomTab(@PathVariable Long customTabId) {
        return customTabService.deleteCustomTab(customTabId);
    }
}
