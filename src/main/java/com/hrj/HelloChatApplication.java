package com.hrj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.hrj.mapper")
public class HelloChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloChatApplication.class, args);
    }

}
