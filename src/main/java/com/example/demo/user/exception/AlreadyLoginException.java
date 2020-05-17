package com.example.demo.user.exception;

public class AlreadyLoginException extends AuthenticationException {

    public AlreadyLoginException() {
    }

    public AlreadyLoginException(String s) {
        super(s);
    }

    public AlreadyLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyLoginException(Throwable cause) {
        super(cause);
    }
}
