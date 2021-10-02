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


    @GetMapping("/")
    public  String ViewHomePage(Model model){
        return home(1, "publishedAt", "desc", model);
    }

    @GetMapping("/home/{offset}")
    public String home(@PathVariable int offset,
                       @RequestParam("sortField") String sortField,
                       @RequestParam("order") String order,
                       Model model) {

//        Page<Post> posts = postService.findAllPostsOrderedByDateDesc(offset);
//        Page<Post> OrderedPostsAsc = postService.findAllPostsOrderedByDateAsc(offset);

        Page<Post> posts = postService.getAllPosts(offset, sortField, order);


//        model.addAttribute("OrderedPostsAsc",OrderedPostsAsc );
        model.addAttribute("posts",posts );
        model.addAttribute("page", offset);
        model.addAttribute("totalPages", posts.getTotalPages());

        model.addAttribute("sortField", sortField);
        model.addAttribute("order", order);

        return "/home";
    }

    @GetMapping("/home/search/{offset}")
    public String search(@PathVariable int offset,
                       @RequestParam(value="sortField", defaultValue = "publishedAt") String sortField,
                       @RequestParam(value = "order", defaultValue = "desc") String order,
                         @RequestParam(value = "search", required = false) String search,
                       Model model) {

//        Page<Post> posts = postService.findAllPostsOrderedByDateDesc(offset);
//        Page<Post> OrderedPostsAsc = postService.findAllPostsOrderedByDateAsc(offset);

        Page<Post> posts = postService.searchPosts(offset, sortField, order, search);


//        model.addAttribute("OrderedPostsAsc",OrderedPostsAsc );
        model.addAttribute("posts", posts);
        model.addAttribute("page", offset);
        model.addAttribute("totalPages", posts.getTotalPages());

        model.addAttribute("sortField", sortField);
        model.addAttribute("order", order);
        model.addAttribute("search", search);

        return "/home";
    }
}
