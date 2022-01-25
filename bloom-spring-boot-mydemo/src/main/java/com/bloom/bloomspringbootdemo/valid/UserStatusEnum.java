package com.bloom.bloomspringbootdemo.valid;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author taosy
 * Created by on 2022-01-13 下午4:01
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {
                            API_ORDER(1, "开放接口下订单请求报文"),

                            GOODS_INFO(2, "商品首营审批快照信息"),

                            SUPPLIER_INFO(3, "供应商首营审批快照信息");

    private final Integer val;
    private final String  desc;

    /**
     * 判断参数合法性
     */
    public static boolean isValidName(Integer name) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (userStatusEnum.getVal().equals(name)) {
                return true;
            }
        }
        return false;
    }
}