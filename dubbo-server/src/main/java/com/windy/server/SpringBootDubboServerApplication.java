package com.windy.server;


import com.windy.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: windy
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@SpringBootApplication
@EnableDubboConfiguration
public class SpringBootDubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDubboServerApplication.class, args);
    }
}
