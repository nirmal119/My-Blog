package com.project.blog.service;

import com.project.blog.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    User save(User user);
}
