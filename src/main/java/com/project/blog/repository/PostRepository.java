package com.project.blog.repository;

import com.project.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(Long id);
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
