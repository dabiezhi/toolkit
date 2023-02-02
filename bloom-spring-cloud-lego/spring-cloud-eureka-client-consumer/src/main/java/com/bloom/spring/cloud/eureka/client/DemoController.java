package com.bloom.spring.cloud.eureka.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author curry
 * Created by on 2023-02-02 2:32 PM
 */
@RestController
public class DemoController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/get")
    public String get() {
        return restTemplate.getForObject("http://eureka-provider/test", String.class)+"11212";
    }
}