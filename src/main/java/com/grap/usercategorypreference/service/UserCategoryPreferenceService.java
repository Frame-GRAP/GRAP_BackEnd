package com.grap.usercategorypreference.service;

import com.grap.category.domain.Category;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import com.grap.usercategorypreference.domain.UserCategoryPreference;
import com.grap.usercategorypreference.dto.UserCategoryPreferenceResponseDto;
import com.grap.usercategorypreference.repository.UserCategoryPreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserCategoryPreferenceService {

    private final UserCategoryPreferenceRepository userCategoryPreferenceRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveUserCategoryPreference(User user, Category category) {

        UserCategoryPreference userCategoryPreference = new UserCategoryPreference();

        userCategoryPreference.mapUser(user);
        userCategoryPreference.mapCategory(category);

        return userCategoryPreferenceRepository.save(userCategoryPreference).getId();
    }

    @Transactional
    public List<UserCategoryPreferenceResponseDto> findCategoryPreferenceByUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        return user.getUserCategoryPreferences().stream()
                .map(UserCategoryPreferenceResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUserCategoryPreference(User user, Category category) {

        UserCategoryPreference userCategoryPreference = userCategoryPreferenceRepository.findByUserAndCategory(user, category).orElseThrow(
                () -> new IllegalArgumentException("유저와 카테고리에 해당하는 객체가 존재하지 않습니다.")
        );

        userCategoryPreferenceRepository.delete(userCategoryPreference);
    }
}
