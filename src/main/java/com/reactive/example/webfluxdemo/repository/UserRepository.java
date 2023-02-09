package com.reactive.example.webfluxdemo.repository;

import com.reactive.example.webfluxdemo.entity.User;
import com.reactive.example.webfluxdemo.model.vo.UserVO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

}
