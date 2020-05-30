package com.example.demo.post.controller;

import com.example.demo.post.dto.CreatePostRequest;
import com.example.demo.post.dto.PostResponse;
import com.example.demo.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostResponse create(HttpSession httpSession, @RequestBody CreatePostRequest createPostRequest) {
        return postService.create(httpSession, createPostRequest);
    }

    @PostMapping("/posts/{id}")
    public void likePost(HttpSession httpSession, @PathVariable Long id) {
        postService.likePost(httpSession, id);
    }

    @GetMapping("/posts")
    public List<PostResponse> getAll() {
        return postService.getAll();
    }

    @GetMapping("/posts/{id}")
    public PostResponse get(@PathVariable Long id) {
        return postService.readPost(id);
    }

    @PutMapping("/posts/{id}")
    public PostResponse update(@PathVariable Long id, @RequestParam String content) {
        return postService.update(id, content);
    }

    @DeleteMapping("/posts/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }
}
