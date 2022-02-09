package com.bloom.rocketmqconsumer.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.ParameterizedType;

/**
 * @author taosy
 * Created by on 2022-02-09 上午10:27
 */
@Slf4j
@Data
public abstract class BaseConsumer<T> implements MessageConsumer<T> {

    private DefaultMQPushConsumer consumer;
    private Class<T>              clz;

    private String                topic;
    private String                tag          = "*";
    private String                consumerName = "DEFAULT";
    private String                group;
    private MessageConfig         messageConfig;
    private String                nameSrvAddr  = "127.0.0.1:9876";

    public BaseConsumer() {
        if (null == getTopic()) {
            throw new RuntimeException("topic is null");
        }
        if (null == getGroup()) {
            throw new RuntimeException("group is null");
        }

        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        clz = (Class<T>) type.getActualTypeArguments()[0];
        consumer = new DefaultMQPushConsumer(getGroup());
        consumer.setInstanceName(getConsumerName());
        consumer.setNamesrvAddr(getNameSrvAddr());

    }

    @PostConstruct
    public void init() {
        start();
    }

    @PreDestroy
    public void destroy() {
        shutdown();
    }

    @Override
    public void start() {
        try {
            if (null != consumer) {
                consumer.subscribe(getTopic(), getTag());
                consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
                    try {
                        if (null != msgs) {
                            msgs.forEach(msg -> {
                                String message = new String(msg.getBody());
                                T obj = JSON.parseObject(message, clz);
                                onMessage(obj);
                            });
                        }
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    } catch (Exception e) {
                        log.error("BaseConsumer consume error,msgs={}", msgs, e);
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                    }
                });
                consumer.start();
            }
        } catch (Exception e) {
            log.error("start BaseConsumer error,topic={},tag={}", getTopic(), getTag(), e);
            throw new RuntimeException("start BaseConsumer error", e);
        }

    }

    @Override
    public void shutdown() {
        if (null != consumer) {
            consumer.shutdown();
        }
    }
}