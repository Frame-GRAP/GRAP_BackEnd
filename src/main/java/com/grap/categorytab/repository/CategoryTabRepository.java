package com.grap.categorytab.repository;

import com.grap.categorytab.domain.CategoryTab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryTabRepository extends JpaRepository<CategoryTab, Long> {
    List<CategoryTab> findByCategoryId(Long categoryId);
}