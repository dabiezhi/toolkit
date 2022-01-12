package com.bloom.bloomspringbootdemo;

import com.bloom.autoconfigure.kaptcha.KaptchaRender;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @Resource
    private KaptchaRender kaptchaRender;

    @RequestMapping(value = "/hello")
    public String hello() {
        return kaptchaRender.render();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
