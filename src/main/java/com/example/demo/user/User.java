package com.example.demo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String password;
    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public User(Long id, String email, String password, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public boolean matchPassword(String passwordQuery) {
        return this.password.equals(passwordQuery);
    }
}


