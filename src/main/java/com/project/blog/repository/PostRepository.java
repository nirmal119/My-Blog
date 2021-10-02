package com.project.blog.repository;

import com.project.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(Long id);
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<Post> findAllByOrderByCreatedAtAsc(Pageable pageable);

//    @Query("SELECT DISTINCT p FROM Post p JOIN p.tags t WHERE p.title LIKE %?1%"+"OR p.content LIKE %?1%"
//    +"OR p.author LIKE %?1%"+"OR t.name LIKE %?1%")
    @Query("SELECT DISTINCT p FROM Post p JOIN p.tags t WHERE p.title LIKE %?1%"+"OR p.content LIKE %?1%"+"OR p.author LIKE %?1%")
    Page<Post> findAll(String keyword, Pageable pageable);

    Page<Post> findAll(Pageable pageable);

}
