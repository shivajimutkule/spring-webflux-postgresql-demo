package com.reactive.example.webfluxdemo.repository;

import com.reactive.example.webfluxdemo.entity.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CompanyRepository extends ReactiveCrudRepository<Company, Long> {
}
