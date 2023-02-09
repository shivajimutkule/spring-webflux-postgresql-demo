package com.reactive.example.webfluxdemo.rest;

import com.reactive.example.webfluxdemo.model.vo.UserVO;
import com.reactive.example.webfluxdemo.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "")
    public Flux<UserVO> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public Mono<UserVO> getUser(@PathVariable Long id) {
        return this.userService.getUser(id);
    }
}
