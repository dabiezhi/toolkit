package com.bloom.spring.boot.ice.config;

import com.bloom.spring.boot.ice.service.SendService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendConfig {

    @Bean
    public SendService sendService2() {
        return new SendService();
    }
}