package com.example.json.controller;

import com.example.json.entity.Post;
import com.example.json.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/fetch")
    public String fetchAndSavePosts() throws JsonProcessingException {
        postService.fetchAndSavePosts();
        return "Posts fetched and saved successfully!";
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }
}
