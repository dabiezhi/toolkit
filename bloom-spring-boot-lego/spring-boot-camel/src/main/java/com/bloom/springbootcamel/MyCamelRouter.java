package com.bloom.springbootcamel;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

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
        from("direct:route-1")
                .log("body")
                .setProperty("p1").constant("p1")
                .process(exchange -> {
                    System.out.println("receive");
                })
//                .pollEnrich("csb-ftp://Administrator@192.168.3.202:21/logs/?password=Guahao@weiyi&binary=true&recursive=true&localWorkDirectory=target/los",
//                        (oldExchange, newExchange) -> {
//                            System.out.println("aaaaaaaa");
//                            return newExchange;
//                        })
//                .to("file://target/lt")
                .process(exchange -> {
                    System.out.println("123213");
                })
        ;
    }
}