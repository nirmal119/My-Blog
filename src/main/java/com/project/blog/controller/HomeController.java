package com.project.blog.controller;

import com.project.blog.entity.Post;
import com.project.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/home/{offset}")
    public String home(@PathVariable int offset,
                       Model model) {

        Page<Post> posts = postService.findAllPostsOrderedByDate(offset);

        model.addAttribute("posts",posts );
        model.addAttribute("page", offset);
        model.addAttribute("totalPages", posts.getTotalPages());

        return "/home";
    }
}
