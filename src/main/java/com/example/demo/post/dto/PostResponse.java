package com.example.demo.post.dto;

import com.example.demo.like.LikeResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {

    private Long id;

    private String content;

    private LikeResponse likeResponse;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public PostResponse(Long id, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, LikeResponse likeResponse) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.likeResponse = likeResponse;
    }
}

