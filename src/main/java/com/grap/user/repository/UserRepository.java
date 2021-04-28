package com.grap.user.repository;

import com.grap.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginIdAndPassword(String loginId, String password);

//    @Query(value="SELECT * FROM User WHERE user_id = ?1 AND password = ?2", nativeQuery = true)
//    User login(String user_id, String password);
}

