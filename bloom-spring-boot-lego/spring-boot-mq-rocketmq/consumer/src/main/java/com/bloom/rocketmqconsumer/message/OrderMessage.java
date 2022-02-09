package com.bloom.rocketmqconsumer.message;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author taosy
 * Created by on 2022-02-09 上午10:42
 */
@Data
public class OrderMessage {

    private String orderNo;
    private String userName;
    private String skuName;

    public static void main(String[] args) {
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setOrderNo("1234");
        orderMessage.setUserName("mao");
        orderMessage.setSkuName("电脑");
        System.out.println(JSON.toJSONString(orderMessage));

    }
}