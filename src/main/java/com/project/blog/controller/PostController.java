package com.project.blog.controller;

import com.project.blog.entity.Post;
import com.project.blog.service.impl.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class PostController {

    private PostService postService;

    public PostController(PostService thePostService) {
        postService = thePostService;
    }

    @PostMapping("/save")
    public String savePost(
            @ModelAttribute("post") @Valid Post post,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/newPost";
        }
        else {
            // save the employee
            postService.save(post);

            // use a redirect to prevent duplicate submissions
            return "redirect:/newPost";
        }
    }
}
