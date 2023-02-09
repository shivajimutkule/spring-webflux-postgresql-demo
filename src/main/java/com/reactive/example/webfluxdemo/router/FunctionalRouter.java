package com.reactive.example.webfluxdemo.router;

import com.reactive.example.webfluxdemo.exception.BaseException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Configuration
public class FunctionalRouter {


    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserRouteHandler routeHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/v2/users")
                        , routeHandler::getAllUsers)
                .andRoute(RequestPredicates.GET("/v2/users/{id}")
                        , routeHandler::getUser);
    }

    @Bean
    public RouterFunction<ServerResponse> testRoutes(TestRouteHandler routeHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/v2/test")
                        , routeHandler::fluxMessage);
    }
}
