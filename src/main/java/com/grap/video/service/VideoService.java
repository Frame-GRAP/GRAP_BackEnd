package com.grap.video.service;

import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.video.domain.Video;
import com.grap.video.dto.VideoResponseDto;
import com.grap.video.dto.VideoSaveRequestDto;
import com.grap.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VideoService {
    public final VideoRepository videoRepository;
    public final GameRepository gameRepository;

    @Transactional
    public Long save(VideoSaveRequestDto requestDto) {
        Optional<Game> gameOp = gameRepository.findByName(requestDto.getGameName());
        if(!gameOp.isPresent()) {
            throw new IllegalArgumentException("영상과 일치하는 게임이 없습니다. gamename =" + requestDto.getGameName());
        }

        Game game = gameOp.get();
        requestDto.setGame(game);
        Video video = requestDto.toEntity();

        return videoRepository.save(video).getId();
    }

    @Transactional
    public VideoResponseDto findById(Long id) {
        Video entity = videoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 영상이 없습니다. id=" + id));

        return new VideoResponseDto(entity);
    }

}
