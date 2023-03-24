package com.bloom.springboot.liteflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.bloom"})
public class SpringBootLiteflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLiteflowApplication.class, args);
    }

}
