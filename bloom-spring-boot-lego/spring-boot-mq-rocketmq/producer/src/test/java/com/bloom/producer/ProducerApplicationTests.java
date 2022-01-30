package com.bloom.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class ProducerApplicationTests {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void sendMessage() {
        String key = "simple";
        for (int i = 0; i < 1; i++) {
            String message = "发送同步消息1,msg=" + i;
            /*
            第一个参数，主题名:标签 topicName:tags
            第二个参数：发送对象
             */
            SendResult sendResult = this.rocketMQTemplate.syncSend(key, message,100000);
            log.info("MQ发送同步消息成功,key={} msg={},sendResult={}", key, message, sendResult);
        }
    }

}
