package com.reactive.example.webfluxdemo.exception;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String code, String errorMessage) {
        super(code, errorMessage);
    }
}
