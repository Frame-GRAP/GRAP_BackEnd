package com.grap.game.controller;

import com.grap.game.dto.GameResponseDto;
import com.grap.game.dto.GameSaveRequestDto;
import com.grap.game.dto.GameUpdateRequestDto;
import com.grap.game.service.GameService;
import com.grap.video.util.VideoCrawling;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class GameApiController {
    private final GameService gameService;
    private final VideoCrawling videoCrawling;

    @PostMapping("/api/game/test")
    public Long save(@RequestBody GameSaveRequestDto requestDto) {
        return gameService.save(requestDto);
    }

    @PostMapping(value = "/api/game")
    public Long save(@RequestPart("img") MultipartFile multipartFile, @RequestPart("dto") GameSaveRequestDto requestDto) {

        try {
            String baseDir = "/home/ec2-user/grap/images/"; // 로컬에서는 /home/ec2-user/grap 부분 변경 필요
            String saveUrl = "http://ec2-3-35-250-221.ap-northeast-2.compute.amazonaws.com:8080/images/";
            String filePath = multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(baseDir + filePath));
            requestDto.setHeaderImg(saveUrl + filePath);

            Long gameId = gameService.save(requestDto);
            videoCrawling.startCrawl(gameId);

            return gameId;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return (long) -1;
    }

    @PutMapping("/api/game/{gameId}")
    public Long update(@PathVariable Long gameId, @RequestBody GameUpdateRequestDto requestDto){
        return gameService.update(gameId, requestDto);
    }

    @GetMapping("/api/game") // 관리자용 임시
    public List<GameResponseDto> findByNameLike(@RequestParam(value = "name") String gameName){
        return gameService.findByNameLike(gameName);
    }

    @GetMapping("/api/game/search")
    public List<GameResponseDto> findByNameContaining(@RequestParam(value = "name") String gameName, @RequestParam int pageNum, @RequestParam int size) {
        return gameService.findByNameContaining(gameName, pageNum, size);
    }

    @GetMapping("/api/game/{gameId}")
    public GameResponseDto findById(@PathVariable Long gameId) {
        return gameService.findById(gameId);
    }

    @GetMapping("/api/game/all")
    public List<GameResponseDto> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/api/game/countAll")
    public Long countAll() {
        return gameService.countAll();
    }

    @GetMapping("api/game/titleAll")
    public List<String> findAllTitles() {
        return gameService.findAllTitles();
    }

    @GetMapping("/api/game/{gameId}/related/all")
    public List<GameResponseDto> findRelatedGameById(@PathVariable Long gameId) {
        return gameService.findRelatedGameById(gameId);
    }

    @GetMapping("/api/game/{gameId}/all")
    public List<GameResponseDto> findByCategoryId(@PathVariable Long gameId, @RequestParam Long categoryId, @RequestParam int size) {
        return gameService.findByCategoryId(gameId, categoryId, size);
    }

    @DeleteMapping("/api/game/{gameId}")
    public Long delete (@PathVariable Long gameId) {
        return gameService.delete(gameId);
    }
}

