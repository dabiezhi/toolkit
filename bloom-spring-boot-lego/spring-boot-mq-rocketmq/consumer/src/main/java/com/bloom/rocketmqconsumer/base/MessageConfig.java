package com.bloom.rocketmqconsumer.base;

import lombok.Data;

/**
 * @author taosy
 * Created by on 2022-02-09 上午10:22
 */
@Data
public class MessageConfig {

    /**
     * 生产者组
     */
    private String  group;
    /**
     * 消息发送超时时间
     */
    private Integer sendMsgTimeOut;
    /**
     * nameSrv地址
     */
    private String  nameSrvAddr;
    /**
     * 实例名称
     */
    private String  instanceName;

}