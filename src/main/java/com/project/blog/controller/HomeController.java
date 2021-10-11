package com.project.blog.controller;

import com.project.blog.entity.Post;
import com.project.blog.service.PostService;
import com.project.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.bind.SchemaOutputResolver;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Controller
public class HomeController {
    private final PostService postService;
    private final TagService tagService;

    @Autowired
    public HomeController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "start", defaultValue = "1") int start,
                       @RequestParam(value = "sortField", defaultValue = "publishedAt") String sortField,
                       @RequestParam(value = "order", defaultValue = "desc") String order,
                       Principal principal,
                       Model model) throws ParseException {
        System.out.println("line1");
        Page<Post> posts = postService.getAllPosts(start, sortField, order);
        System.out.println("line2");
        List<String> authors = postService.getAuthors();
        System.out.println("line3");
        List<Date> publishDates = postService.getPublishDate();
        System.out.println("line4");
        Set<LocalDate> dates = new HashSet<>();
        System.out.println("line5");

        for(Date date : publishDates){
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Instant instant = date.toInstant();
            LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
            dates.add(localDate);
        }
        List<String> tags = tagService.getTags();
        model.addAttribute("tags", tags);
        model.addAttribute("authors", authors);
        model.addAttribute("publishedAt", dates);
        model.addAttribute("posts",posts );
        model.addAttribute("page", start);
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("order", order);
        if (hasRole("ROLE_USER") || hasRole("ROLE_ADMIN")) {
            model.addAttribute("username", principal.getName());
        }
        System.out.println("line1");
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "start", defaultValue = "1") int start,
                       @RequestParam(value="sortField", defaultValue = "publishedAt") String sortField,
                       @RequestParam(value = "order", defaultValue = "desc") String order,
                         @RequestParam(value = "search", required = false) String search,
                         Principal principal,
                       Model model) {
        Page<Post> posts = postService.searchPosts(start, sortField, order, search);
        List<String> authors = postService.getAuthors();
        List<String> tags = tagService.getTags();
        List<Date> publishDates = postService.getPublishDate();

        Set<LocalDate> dates = new HashSet<>();

        for(Date date : publishDates){
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Instant instant = date.toInstant();
            LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
            dates.add(localDate);
        }

        model.addAttribute("tags", tags);
        model.addAttribute("authors", authors);
        model.addAttribute("posts", posts);
        model.addAttribute("page", start);
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("order", order);
        model.addAttribute("search", search);
        model.addAttribute("publishedAt", dates);
        if (hasRole("ROLE_USER") || hasRole("ROLE_ADMIN")) {
            model.addAttribute("username", principal.getName());
        }
        return "/home";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(value = "start", defaultValue = "1") int start,
                         @RequestParam(value="sortField", defaultValue = "publishedAt") String sortField,
                         @RequestParam(value = "order", defaultValue = "desc") String order,
                         @RequestParam(value = "author", required = false) List<String> authors,
                         @RequestParam(value = "tags", required = false) List<String> tags,
                         @RequestParam(value = "publishedAt", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) List<Date> publishedAt,

                         Model model) throws ParseException {

        if(publishedAt == null){
            publishedAt = new ArrayList<>();
            String date = "0001-02-01";
            Date defaultDate=new SimpleDateFormat("yyyy-MM-dd").parse(date);
            publishedAt.add(defaultDate);
        }
        if(authors == null){
            authors = new ArrayList<>();
            authors.add("~");
        }if(tags == null){
            tags = new ArrayList<>();
            tags.add("~");
        }

        Page<Post> posts = postService.getPageOfFilteredPost(authors, tags, publishedAt, start, sortField, order);
        model.addAttribute("posts",posts);
        model.addAttribute("page",start);
        return "/filter";
    }

    private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getAuthor());
    }

    private boolean hasRole(String role)
    {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
}