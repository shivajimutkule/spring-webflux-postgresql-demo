package com.reactive.example.webfluxdemo.exception.fn;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {


    public GlobalExceptionHandler(GlobalErrorAttributes g, ApplicationContext applicationContext,
                                  ServerCodecConfigurer serverCodecConfigurer) {
        super(g, new WebProperties.Resources(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(),this::renderException);
    }

    private Mono<ServerResponse> renderException(ServerRequest request) {
        Map<String, Object> error = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        error.remove("status");
        error.remove("requestId");
        return ServerResponse.status(HttpStatus.valueOf(Integer.parseInt(String.valueOf(error.get("code")))))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(error));

    }
}