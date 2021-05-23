package com.grap.usergamepreference.repository;

import com.grap.usergamepreference.domain.UserGamePreference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGamePreferenceRepository extends JpaRepository<UserGamePreference, Long> {

    UserGamePreference findTop1ByUserIdAndGameIdOrderByIdDesc(Long userId, Long gameId);

    UserGamePreference findTop1ByUserIdOrderByIdDesc(Long userId);
}
