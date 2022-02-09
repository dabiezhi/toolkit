package com.bloom.rocketmqconsumer.consumer;

import com.alibaba.fastjson.JSON;
import com.bloom.rocketmqconsumer.base.BaseConsumer;
import com.bloom.rocketmqconsumer.message.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author taosy
 * Created by on 2022-02-09 上午10:45
 * 例子：https://jingyan.baidu.com/article/359911f5245dcb17ff03066a.html
 */
@Component
@Slf4j
public class OrderConsumer extends BaseConsumer<OrderMessage> {

    @Override
    public String getTopic() {
        return "order-topic";
    }

    @Override
    public String getGroup() {
        return "order-consumer-group";
    }

    @Override
    public void onMessage(OrderMessage message) {
        System.out.println("OrderConsumer=======" + JSON.toJSONString(message));
    }
}