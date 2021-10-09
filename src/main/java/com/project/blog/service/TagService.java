package com.project.blog.service;

import java.util.List;
import java.util.Optional;

public interface TagService {

    List<String> getTags();

    Optional<Long> getTagId(String tagName);
}
