package com.grap.customtab.service;

import com.grap.customtab.domain.CustomTab;
import com.grap.customtab.dto.CustomTabResponseDto;
import com.grap.customtab.dto.CustomTabSaveRequestDto;
import com.grap.customtab.repository.CustomTabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomTabService {

    private final CustomTabRepository customTabRepository;

    @Transactional(readOnly = true)
    public List<CustomTabResponseDto> findAllCustomTab() {
        return customTabRepository.findAll().stream()
                .map(CustomTabResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long saveCustomTab(CustomTabSaveRequestDto requestDto) {

        if(customTabRepository.findByName(requestDto.getName()).isEmpty()) {
            return customTabRepository.save(requestDto.toEntity()).getId();
        }

        return (long) -1;
    }

    @Transactional
    public Long deleteCustomTab(Long customTabId) {

        CustomTab customTab = customTabRepository.findById(customTabId).orElseThrow(
                () -> new IllegalArgumentException("해당 탭은 존재하지 않습니다.")
        );

        customTabRepository.delete(customTab);

        return customTabId;
    }
}
