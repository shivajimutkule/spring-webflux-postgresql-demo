package com.reactive.example.webfluxdemo.router;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class TestRouteHandler {
    public Mono<ServerResponse> fluxMessage(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(
                        Flux.interval(Duration.ofSeconds(2))
                                .delayElements(Duration.ofSeconds(1)).log(), Integer.class
                );
    }
}
