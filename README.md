# spring-webflux-postgresql-demo

# Read Me First
The following was discovered as part of building this project:

this project uses 'com.reactive.example.webfluxdemo' package.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/#build-image)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#web.reactive)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#data.sql.r2dbc)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Accessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)

### Additional Links
These additional references should also help you:

* [R2DBC Homepage](https://r2dbc.io)

## Postgres Setup
* connect to postgres server using ```psql -U postgres -h localhost```

```roomsql
create database test;
create user test with encrypted password 'test123'
grant all privileges on database test to test;
```

### create tables
#### create user table
```roomsql
CREATE TABLE if not exists user_t (
    id serial primary key,
    name VARCHAR(30),
    company_id bigint,
    created_by bigint NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_by bigint NOT NULL
);

```
#### create company table
```roomsql
CREATE TABLE if not exists company_t (
    id serial primary key,
    name VARCHAR(30),
    city VARCHAR(20),
    created_by bigint NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_by bigint NOT NULL
);
```
#### Insert data into users
```roomsql
insert into user_t (name, company_id, created_by, last_updated_by) values('shivaji', 1, 1,1);
insert into user_t (name, company_id, created_by, last_updated_by) values('nitin', 1, 1,1);
insert into user_t (name, company_id, created_by, last_updated_by) values('sachin', 5, 1,1);
```
#### insert data into company
```roomsql
insert into company_t  (name, city, created_by, last_updated_by) values('abc pvt', 'pune', 1, 1);
insert into company_t  (name, city, created_by, last_updated_by) values('test pvt', 'pune', 1, 1);
```
#### REST Controller APIs
* http://localhost:8080/v1/users
* http://localhost:8080/v1/users/:id

#### Functional Endpoints
* http://localhost:8080/v2/users
* http://localhost:8080/v2/users/:id

### Features:
* Rest Controller as well as functional endpoint approach
* Global Exception handler
* Custom database converter
