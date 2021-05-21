package com.grap.customtab.repository;

import com.grap.customtab.domain.CustomTab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomTabRepository extends JpaRepository<CustomTab, Long> {

    Optional<CustomTab> findByName(String name);
}
