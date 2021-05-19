package com.grap.game.controller;

import com.grap.game.dto.GameResponseDto;
import com.grap.game.dto.GameSaveRequestDto;
import com.grap.game.dto.GameUpdateRequestDto;
import com.grap.game.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
public class GameApiController {
    private final GameService gameService;

    @PostMapping("/api/game/test")
    public Long save(@RequestBody GameSaveRequestDto requestDto) {
        return gameService.save(requestDto);
    }

    @PostMapping(value = "/api/game")
    public Long save(@RequestPart("img") MultipartFile multipartFile, @RequestPart("dto") GameSaveRequestDto requestDto) {

        try {
            String baseDir = "/home/ec2-user/grap/GRAP_BackEnd/src/main/resources/static/header-img"; // 로컬에서는 /home/ec2-user/grap <- 변경 필요
            String filePath = baseDir + "/" + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(filePath));
            requestDto.setHeaderImg(filePath);

            return gameService.save(requestDto);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return (long) -1;
    }

    @PutMapping("/api/game/{gameId}")
    public Long update(@PathVariable Long gameId, @RequestBody GameUpdateRequestDto requestDto){
        return gameService.update(gameId, requestDto);
    }

    @GetMapping("/api/game/{gameId}")
    public GameResponseDto findById(@PathVariable Long gameId) {

        return gameService.findById(gameId);
    }

    @GetMapping("/api/game/all")
    public List<GameResponseDto> findAll() {
        return gameService.findAll();
    }

    @GetMapping("/api/admin/game/{gameId}")
    public GameResponseDto findByIdForAdmin(HttpServletResponse response, @PathVariable Long gameId) {
        response.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
        response.addHeader("X-Total-Count", String.valueOf(1));
        return gameService.findById(gameId);
    }

    @GetMapping("/api/admin/game/all")
    public List<GameResponseDto> findAllForAdmin(HttpServletResponse response) {
        response.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
        response.addHeader("X-Total-Count", String.valueOf(gameService.findAll().size()));
        return gameService.findAll();
    }

}

