package com.example.demo.post.repository;

import com.example.demo.post.domain.Post;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Primary
@Repository
public class InMemoryPostRepository implements PostRepository {

    private Map<Long, Post> posts = new HashMap<>();
    private Long nextId = 0L;

    @PostConstruct
    private void  postConstruct() {
        System.out.println("InMemoryPostRepository가 생성되었습니다.");
    }

    @Override
    public Post create(Post post) {
        post.setId(nextId);
        posts.put(post.getId(), post);
        nextId++;
        return post;
    }

    @Override
    public Post getById(Long id) {
        if (posts.containsKey(id)) {
            return posts.get(id);
        }
        throw new IllegalArgumentException("해당 id의 사용자가 없습니다.");
    }

    @Override
    public Post update(Post post) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
