package com.grap.usercategorypreference.repository;

import com.grap.category.domain.Category;
import com.grap.user.domain.User;
import com.grap.usercategorypreference.domain.UserCategoryPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCategoryPreferenceRepository extends JpaRepository<UserCategoryPreference, Long> {
    Optional<UserCategoryPreference> findByUserId(Long userId);

    Optional<UserCategoryPreference> findByUserAndCategory(User user, Category category);

    Optional<UserCategoryPreference> findByUser(User user);

    Optional<List<UserCategoryPreference>> findAllByUser(User user);
}
