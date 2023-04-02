package com.bloom.springboot.liteflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan({"com.bloom"})
@EnableAspectJAutoProxy
public class SpringBootLiteflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLiteflowApplication.class, args);
    }

}
