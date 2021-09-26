package com.project.blog.service.impl;

import com.project.blog.entity.Post;
import com.project.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository thePostRepository) {
        postRepository = thePostRepository;
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }
}
