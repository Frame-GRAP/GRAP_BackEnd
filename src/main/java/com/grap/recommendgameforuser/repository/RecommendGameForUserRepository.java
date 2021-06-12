package com.grap.recommendgameforuser.repository;

import com.grap.recommendgameforuser.domain.RecommendGameForUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface RecommendGameForUserRepository extends JpaRepository<RecommendGameForUser, Long> {
    List<RecommendGameForUser> findByUserId(Long userId);

    @Transactional
    @Modifying
    @Query("delete from RecommendGameForUser")
    void customDeleteAll();
}