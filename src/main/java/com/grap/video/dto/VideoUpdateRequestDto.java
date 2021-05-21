package com.grap.video.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VideoUpdateRequestDto {

    private boolean isRegistered;

    @Builder
    public VideoUpdateRequestDto(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
}
