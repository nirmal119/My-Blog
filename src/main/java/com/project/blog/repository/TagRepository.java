package com.project.blog.repository;

import com.project.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
    @Query("SELECT DISTINCT t.name from Tag t")
    List<String> findAllNames();

    @Query("SELECT DISTINCT t.id from Tag t WHERE t.name LIKE %?1%")
    Optional<Long> findAll(String tagName);
}