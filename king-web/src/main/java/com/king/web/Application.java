package com.king.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 * 如果此类的包为com.king，就用不着配置扫描了
 * 2016.12.26
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.king")
@EnableJpaRepositories(basePackages = "com.king.data.repository")
@EntityScan("com.king.data.model")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}