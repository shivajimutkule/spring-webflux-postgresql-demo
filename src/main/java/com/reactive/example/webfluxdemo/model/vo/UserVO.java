package com.reactive.example.webfluxdemo.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reactive.example.webfluxdemo.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO {
    private long id;
    private String name;
    private long companyId;
    private long createdBy;
    private Date createdAt;
    private long lastUpdatedBy;
    private Date lastUpdatedAt;
    private Company company;

}

