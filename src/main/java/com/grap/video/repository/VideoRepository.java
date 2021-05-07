package com.grap.video.repository;

import com.grap.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("SELECT p FROM Video p ORDER BY p.id DESC")
    List<Video> findAllDesc();
}
