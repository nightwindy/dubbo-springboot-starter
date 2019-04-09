package com.windy.client.config;

import com.windy.dubbo.spring.boot.hystrix.Fallback;

/**
 * @Author: windy
 * @Date: 2019-04-09 10:54
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
public class HelloFallBack implements Fallback {
    @Override
    public Object invoke() {
        return "hello fall back";
    }
}
