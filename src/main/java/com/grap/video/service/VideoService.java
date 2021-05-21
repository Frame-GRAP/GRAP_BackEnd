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

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VideoService {

    private final VideoRepository videoRepository;
    private final GameRepository gameRepository;

    @Transactional
    public Long save(VideoSaveRequestDto requestDto, Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("영상과 일치하는 게임이 없습니다. Id =" + gameId));

        Video video = requestDto.toEntity();
        video.mapGame(game);

        return videoRepository.save(video).getId();
    }

    @Transactional
    public VideoResponseDto findById(Long videoId) {
        Video entity = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 영상이 없습니다. id=" + videoId));

        return new VideoResponseDto(entity);
    }

    @Transactional
    public List<VideoResponseDto> findByGameId(Long gameId) {
        return videoRepository.findByGameId(gameId).stream()
                .map(VideoResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long delete(Long videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(
                () -> new IllegalArgumentException("영상과 일치하는 게임이 없습니다. Id =" + videoId));

        videoRepository.deleteById(video.getId());
        return video.getId();
    }

    @Transactional
    public Long updateIsRegistered(Long videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(
                () -> new IllegalArgumentException("해당 영상은 존재하지 않습니다.")
        );

        video.revert();

        return videoId;
    }
}
