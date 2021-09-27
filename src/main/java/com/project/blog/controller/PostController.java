package com.project.blog.controller;

import com.project.blog.entity.Post;
import com.project.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
//@RequestMapping("/posts")
public class PostController {

    private PostService postService;


    public PostController(PostService thePostService) {
        postService = thePostService;
    }

    @GetMapping("/")
    public String homePage(Model model){
        Post post = new Post();
        post.setAuthor("Nirmal Kumar");
        model.addAttribute("post", post);
        return "newPost";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("post") Post post,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "fail";
        }
        else {

            postService.save(post);


            return "redirect:/";
        }
    }
}
