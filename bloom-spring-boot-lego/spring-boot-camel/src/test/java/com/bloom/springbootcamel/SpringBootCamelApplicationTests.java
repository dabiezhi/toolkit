package com.bloom.springbootcamel;

import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = SpringBootCamelApplication.class)
class SpringBootCamelApplicationTests {

    @Resource
    private ProducerTemplate producerTemplate;

    @Test
    void contextLoads() {
        Map<String, Object> body = new HashMap<>();
        body.put("k1", "v1");
        body.put("k2", "v2");

        Object result = producerTemplate.sendBody("direct:route-1", ExchangePattern.InOut, body);
        System.out.println(result);
    }

}
