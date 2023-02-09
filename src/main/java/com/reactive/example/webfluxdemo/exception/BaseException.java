package com.reactive.example.webfluxdemo.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseException extends Exception {
    private String code;
    private String message;

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
