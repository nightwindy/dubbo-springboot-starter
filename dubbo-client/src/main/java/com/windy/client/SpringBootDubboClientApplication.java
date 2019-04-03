package com.windy.client;

import com.windy.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class SpringBootDubboClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboClientApplication.class, args);
    }
}
