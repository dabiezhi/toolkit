package com.bloom.rocketmqconsumer.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author taosy
 * Created by on 2022-01-25 下午9:10
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "simple", //主题
        consumerGroup = "simple-consumer-group"//消费组
)
public class SimpleConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("消费者======" + s);
    }
}