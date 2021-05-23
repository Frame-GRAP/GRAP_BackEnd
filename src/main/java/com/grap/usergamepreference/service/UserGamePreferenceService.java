package com.grap.usergamepreference.service;

import com.grap.category.domain.Category;
import com.grap.category.repository.CategoryRepository;
import com.grap.game.domain.Game;
import com.grap.game.repository.GameRepository;
import com.grap.gameandcategory.repository.GameAndCategoryRepository;
import com.grap.user.domain.User;
import com.grap.user.repository.UserRepository;
import com.grap.usercategorypreference.domain.UserCategoryPreference;
import com.grap.usercategorypreference.repository.UserCategoryPreferenceRepository;
import com.grap.usercategorypreference.service.UserCategoryPreferenceService;
import com.grap.usergamepreference.domain.UserGamePreference;
import com.grap.usergamepreference.dto.UserGamePreferenceSaveRequestDto;
import com.grap.usergamepreference.repository.UserGamePreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserGamePreferenceService {

    private final UserGamePreferenceRepository userGamePreferenceRepository;
    private final UserCategoryPreferenceRepository userCategoryPreferenceRepository;
    private final GameAndCategoryRepository gameAndCategoryRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final UserCategoryPreferenceService userCategoryPreferenceService;

    @Transactional
    public Long saveUserGamePreferences(Long userId, List<UserGamePreferenceSaveRequestDto> requestDtos) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        int count = 0;

        for(UserGamePreferenceSaveRequestDto requestDto : requestDtos) {

            UserGamePreference userGamePreference = new UserGamePreference();

            userGamePreference.mapUser(user);
            userGamePreference.mapGame(gameRepository.findById(requestDto.getGameId()).orElseThrow(
                    () -> new IllegalArgumentException("해당 게임는 존재하지 않습니다.")
            ));

            userGamePreferenceRepository.save(userGamePreference);

            count++;
        }
        calculateCategoryPreference(user);

        return (long) count;
    }

    public void saveFavor(User user, Game game) {

        UserGamePreference userGamePreference = new UserGamePreference();

        userGamePreference.mapUser(user);
        userGamePreference.mapGame(game);

        userGamePreferenceRepository.save(userGamePreference);

        calculateCategoryPreference(user);
    }

    public void deleteGamePreference(Long userId, Long gameId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        UserGamePreference userGamePreference = userGamePreferenceRepository.findTop1ByUserIdAndGameIdOrderByIdDesc(userId, gameId);
        userGamePreferenceRepository.delete(userGamePreference);

        calculateCategoryPreference(user);
    }

    public void calculateCategoryPreference(User user) {

        List<Game> userGamePreferences = user.getUserGamePreferences().stream().map(UserGamePreference::getGame).collect(Collectors.toList());
        List<Long> userCategoryPreferences = new ArrayList<>();

        for(Game userGamePreference : userGamePreferences) {
            userCategoryPreferences.addAll(gameAndCategoryRepository.findByGameId(userGamePreference.getId()).stream().map(item -> item.getCategory().getId()).collect(Collectors.toList()));
        }

        Set<Long> distinct = new HashSet<>(userCategoryPreferences);

        Optional<List<UserCategoryPreference>> userCategoryPreference = userCategoryPreferenceRepository.findAllByUser(user);
        userCategoryPreference.ifPresent(userCategoryPreferenceRepository::deleteAll);

        HashMap<Long, Long> result = new HashMap<>();

        for(Long d : distinct) {
            result.put(d, (long) Collections.frequency(userCategoryPreferences, d));
        }

        Map<Long, Long> topThree =
                result.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .limit(3)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));



        for (Long k : topThree.keySet()) {
            Category category = categoryRepository.findById(k).orElseThrow(
                    () -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다.")
            );

            userCategoryPreferenceService.saveUserCategoryPreference(user, category);
        }
    }
}
