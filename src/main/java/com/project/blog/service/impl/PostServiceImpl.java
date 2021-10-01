package com.project.blog.service.impl;

import com.project.blog.service.PostService;
import com.project.blog.entity.Post;
import com.project.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository thePostRepository) {
        postRepository = thePostRepository;
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public Optional<Post> findPostById(int id) {
        return postRepository.findById(id);
    }

    @Override
    public Page<Post> findAllPostsOrderedByDate(int page) {
        return postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, 2));
    }
}
