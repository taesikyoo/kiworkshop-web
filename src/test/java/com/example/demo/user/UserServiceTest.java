package com.example.demo.user;

import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class UserServiceTest {

    @Test
    void passwordEncryption() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SH-256");
        String password = "abc";
        messageDigest.update(password.getBytes());


    }
}