package com.grap.video.controller;

import com.grap.video.dto.VideoResponseDto;
import com.grap.video.dto.VideoSaveRequestDto;
import com.grap.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class VideoApiController {
    private final VideoService videoService;

    @PostMapping("/api/video")
    public Long save(@RequestBody VideoSaveRequestDto requestDto) {
        return videoService.save(requestDto);
    }

    @GetMapping("/api/video/{id}")
    public VideoResponseDto findById(@PathVariable Long id) {
        return videoService.findById(id);
    }

    @GetMapping("/api/video/all")
    public List<VideoResponseDto> findAllDesc() {
        return videoService.findAllDesc();
    }

    @DeleteMapping("/api/video/{id}")
    public int delete (@PathVariable Long id) {
        return videoService.delete(id);
    }
}
