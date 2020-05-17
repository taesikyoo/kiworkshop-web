package com.example.demo.post;

import com.example.demo.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Post {

    @Setter
    private Long id;
    private String content;
    private int likeCount;
    private User author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Post(String content, User author) {
        this.content = content;
        this.likeCount = 0;
        this.author = author;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }
}
