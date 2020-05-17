package com.example.demo.user.exception;

public class PasswordNotMatchedException extends AuthenticationException {

    public PasswordNotMatchedException() {
    }

    public PasswordNotMatchedException(String s) {
        super(s);
    }

    public PasswordNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchedException(Throwable cause) {
        super(cause);
    }
}
