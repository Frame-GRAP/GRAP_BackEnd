package com.grap.categorytab.repository;

import com.grap.categorytab.domain.CategoryTab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryTabRepository extends JpaRepository<CategoryTab, Long> {
    List<CategoryTab> findByCategoryId(Long categoryId);

    @Transactional
    @Modifying
    @Query("delete from CategoryTab")
    void customDeleteAll();
}