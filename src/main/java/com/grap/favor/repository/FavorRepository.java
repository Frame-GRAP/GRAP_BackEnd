package com.grap.favor.repository;

import com.grap.favor.domain.Favor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FavorRepository extends JpaRepository<Favor, Long> {
    Optional<Favor> findByUserIdAndGameId(Long userId, Long gameId);
}