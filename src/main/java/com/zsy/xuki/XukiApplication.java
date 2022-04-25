package com.zsy.xuki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.zsy.xuki.mapper")
@EntityScan(basePackages = "com.zsy.xuki.entity")
@SpringBootApplication
public class XukiApplication {

    public static void main(String[] args) {
        SpringApplication.run(XukiApplication.class, args);
    }

}
