package com.example.demo.post.controller;

import com.example.demo.post.domain.Post;
import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{id}")
    public Post get(@PathVariable Long id) {
        return postService.get(id);
    }

    @PostMapping("/posts")
    public Post post(HttpSession httpSession, @RequestBody CreatePostRequest createPostRequest) {
        return postService.create(httpSession, createPostRequest);
    }

    @PutMapping("/posts")
    public Post put() {
        return postService.update();
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }
}
