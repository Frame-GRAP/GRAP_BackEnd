package com.grap.gameandcategory.repository;

import com.grap.gameandcategory.domain.GameAndCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameAndCategoryRepository extends JpaRepository<GameAndCategory, Long> {

    List<GameAndCategory> findByGameId(Long gameId);

    List<GameAndCategory> findByCategoryId(Long categoryId);

    Page<GameAndCategory> findByCategoryIdAndGameIdLessThanOrderByGameIdDesc(Long categoryId, Long gameId, Pageable pageable);
}