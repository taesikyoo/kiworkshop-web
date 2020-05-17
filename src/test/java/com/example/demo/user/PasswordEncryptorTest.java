package com.example.demo.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptorTest {

    @Test
    void name() {
        String password = "ABC";
        String correctPw = "ABC";
        String wrongPw = "abc";

        String encrypted = PasswordEncryptor.encrypt(password);
    }
}