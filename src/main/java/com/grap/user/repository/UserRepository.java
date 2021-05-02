package com.grap.user.repository;

import com.grap.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginIdAndPassword(String loginId, String password);

    Optional<User> findByLoginId(String loginId);

//    @Query(value="SELECT * FROM User WHERE user_id = ?1 AND password = ?2", nativeQuery = true)
//    User login(String user_id, String password);
}

