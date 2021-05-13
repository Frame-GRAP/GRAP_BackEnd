package com.grap.video.controller;

import com.grap.video.dto.VideoResponseDto;
import com.grap.video.dto.VideoSaveRequestDto;
import com.grap.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/api/game/{gameId}/video/all")
    public List<VideoResponseDto> findByGameId(@PathVariable Long gameId) {
        return videoService.findByGameId(gameId);
    }

    @DeleteMapping("/api/game/{gameId}/video/{videoId}")
    public Long delete (@PathVariable Long videoId) {
        return videoService.delete(videoId);
    }

    @GetMapping("/api/admin/game/{gameId}/video/all")
    public List<VideoResponseDto> findByGameId(HttpServletResponse response, @PathVariable Long gameId) {
        response.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
        response.addHeader("X-Total-Count", String.valueOf(videoService.findByGameId(gameId).size()));
        return videoService.findByGameId(gameId);
    }
}
