package com.reactive.example.webfluxdemo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
@Slf4j
class RestExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ExceptionResponse> handle(BaseException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(ex.getCode());
        exceptionResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(Integer.parseInt(ex.getCode())).body(exceptionResponse);
    }

}
