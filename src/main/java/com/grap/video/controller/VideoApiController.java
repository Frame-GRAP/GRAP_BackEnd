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

    @PostMapping("/api/game/{gameId}/video")
    public Long save(@RequestBody VideoSaveRequestDto requestDto, @PathVariable Long gameId) {
        return videoService.save(requestDto, gameId);
    }

    @GetMapping("/api/video/countAll")
    public Long countAll() {
        return videoService.countAll();
    }

    @GetMapping("/api/video/{videoId}")
    public VideoResponseDto findById(@PathVariable Long videoId) {
        return videoService.findById(videoId);
    }

    @GetMapping("/api/game/{gameId}/video/all")
    public List<VideoResponseDto> findByGameId(@PathVariable Long gameId) {
        return videoService.findByGameId(gameId);
    }

    @DeleteMapping("/api/video/{videoId}")
    public Long delete(@PathVariable Long videoId) {
        return videoService.delete(videoId);
    }

    @PutMapping("/api/video/{videoId}")
    public Long updateIsRegistered(@PathVariable Long videoId) {
        return videoService.updateIsRegistered(videoId);
    }

}
