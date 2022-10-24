package com.bloom.springbootcamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author curry
 * Created by on 2022-10-13 下午2:56
 */
@Component
public class MyCamelRouter extends RouteBuilder {

    @Resource
    private MyBean myBean;

    @Override
    public void configure() throws Exception {
        from("direct:route-1").log("body").setProperty("p1").constant("p1").process(exchange -> {
            System.out.println("receive");
        }).process(exchange -> {
            System.out.println("123213");
            exchange.getIn().setBody("我是库里");
        }).to("direct:route-2");

        from("direct:route-2").log("body").setProperty("p1").constant("p1").process(exchange -> {
            System.out.println(exchange.getIn().getBody());
            exchange.getIn().setBody("我是格林");
        });

    }
}