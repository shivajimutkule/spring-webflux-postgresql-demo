package com.reactive.example.webfluxdemo.service;

import com.reactive.example.webfluxdemo.entity.Company;
import com.reactive.example.webfluxdemo.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Mono<Company> getCompany(Long id) {
        return this.companyRepository.findById(id);
    }
}
