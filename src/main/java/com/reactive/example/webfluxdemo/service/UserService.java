package com.reactive.example.webfluxdemo.service;

import com.reactive.example.webfluxdemo.entity.Company;
import com.reactive.example.webfluxdemo.entity.User;
import com.reactive.example.webfluxdemo.exception.UserNotFoundException;
import com.reactive.example.webfluxdemo.model.vo.UserVO;
import com.reactive.example.webfluxdemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final CompanyService companyService;

    public UserService(UserRepository userRepository,
                       CompanyService companyService) {
        this.userRepository = userRepository;
        this.companyService = companyService;
    }

    public Flux<UserVO> getAllUsers() {
        logger.atDebug()
                .log("calling getAll Users method");
        return userRepository.findAll()
                .switchIfEmpty(Flux.defer(() ->
                    Flux.error(new UserNotFoundException("404", "Users not found"))
                ))
                .flatMap(user -> this.companyService.getCompany(user.getCompanyId())
                            .switchIfEmpty(Mono.defer(() -> {
                                logger.atWarn()
                                        .addArgument(user.getCompanyId())
                                        .log("company_id = {} not available");
                                return Mono.just(Company.builder().id(user.getCompanyId()).build());
                            }))
                            .map(company -> {
                                UserVO userVO = getUserVO(user);
                                userVO.setCompany(company);
                                return userVO;
                            })
                ).onErrorResume(error -> {
                    logger.atError()
                            .setCause(error)
                            .addArgument(error.getMessage())
                            .log("error occurred: {}");
                    return Mono.error(error);
                }).log();
    }

    public Mono<UserVO> getUser(Long id) {
        return this.userRepository.findById(id)
                .switchIfEmpty(Mono.defer(() ->
                        Mono.error(new UserNotFoundException("404", String.format("User = %d not found", id)))
                ))
                .map(UserService::getUserVO);
    }

    private static UserVO getUserVO(User user) {
        return UserVO.builder()
                .id(user.getId())
                .name(user.getName())
                .companyId(user.getCompanyId())
                .createdBy(user.getCreatedBy())
                .createdAt(user.getCreatedAt())
                .lastUpdatedAt(user.getLastUpdatedAt())
                .lastUpdatedBy(user.getLastUpdatedBy())
                .build();
    }
}
