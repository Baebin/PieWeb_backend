# **PieWeb_backend**

Piebin's Web Project

___

## 0. Index

1. [Environment](#1-environment)
2. [API](#2-api)

___

## 1. Environment

- *(IDE: IntelliJ IDEA Ultimate 2022.03)*
- Language: Java (JDK 19)
- Build Tool: Gradle
- Using Library:
  1. [Spring-Boot-Starter-Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
  2. [Spring-Boot-Starter-Data-Jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
  3. [Spring-Boot-Starter-Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
  4. [Spring-Boot-Starter-Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
  5. [Spring-Boot-Starter-Devtools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-devtools)

___

## 2. API

| Type | Function | Method | URL | Parameter | Return |
| --- | --- | --- | --- | --- | --- |
| Account | register | POST | /api/signup | | ok |
| | login | POST | /api/signin | | ok |
| |
| Post | save | POST | /api/post/save | | ok |
| | getPosts | GET | /api/posts | ?page={page}&size={size} | Page<Post> |
