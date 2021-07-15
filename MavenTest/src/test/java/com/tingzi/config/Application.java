package com.tingzi.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author xiexiaoting
 * @Date 2021/1/11 14:00
 * @Version 1.0
 */

//springboot总配置，执行这个main方法，可一次性执行被springboot托管的所有方法
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
@ComponentScan("com.tingzi.test")  //扫描哪一个包下面的类
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(com.tingzi.config.Application.class, args);
    }
}