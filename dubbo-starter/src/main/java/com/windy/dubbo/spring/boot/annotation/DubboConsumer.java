package com.windy.dubbo.spring.boot.annotation;

import java.lang.annotation.*;

/**
 * @Author: windy
 * @Date: 2019-04-02 16:16
 * @Version 1.0
 * @mail nightwindy163@gmail.com
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DubboConsumer {
    String version() default "";

    int timeout() default 0;

    String registry() default "";

    String group() default "";

    String client() default "";

    String url() default "";

    String protocol() default "";

    boolean check() default true;

    boolean lazy() default false;

    int retries() default 0;

    int actives() default 0;

    String loadbalance() default "";

    boolean async() default false;

    boolean sent() default false;
}
