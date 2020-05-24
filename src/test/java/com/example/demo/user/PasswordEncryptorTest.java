package com.example.demo.user;

import com.example.demo.user.util.PasswordEncryptor;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordEncryptorTest {

    @Test
    void name() {
        String password = "ABC";
        String correctPw = "ABC";
        String wrongPw = "abc";

        String encrypted = PasswordEncryptor.encrypt(password);
    }
}