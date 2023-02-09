package com.reactive.example.webfluxdemo.router;

import com.reactive.example.webfluxdemo.model.vo.UserVO;
import com.reactive.example.webfluxdemo.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.time.Duration;
@Component
public class UserRouteHandler {

    private final UserService userService;
    public UserRouteHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(
                        this.userService.getAllUsers()
                                .delayElements(Duration.ofSeconds(1)).log(), UserVO.class
                );
    }

    public Mono<ServerResponse> getUser(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        this.userService.getUser(Long.valueOf(request.pathVariable("id"))
                                ).log(), UserVO.class
                );
    }

}
