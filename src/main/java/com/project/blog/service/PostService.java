package com.project.blog.service;

import com.project.blog.entity.Post;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface PostService {

    public void save(Post post);
    Optional<Post> findPostById(int id);
}
