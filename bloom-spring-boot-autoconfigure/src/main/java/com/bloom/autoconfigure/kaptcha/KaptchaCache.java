/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.autoconfigure.kaptcha;

/**
 * 验证码组件接口
 *
 * @author taosy
 * @version V1.0
 * @since 2022-01-05 17:25
 */
public interface KaptchaCache {

    /**
     * 缓存验证码（待实现）
     *
     * @param code 需要验证的字符串
     * @return 是否缓存成功
     */
    default boolean setCode(String code) {
        return true;
    }

    /**
     * 校对验证码,默认超时15分钟（900s）（待实现）
     *
     * @param code 需要验证的字符串
     * @return 是否验证成功
     */
    default boolean verifyCode(String code) {
        return true;
    }

    /**
     * 校对验证码(待实现)
     *
     * @param code   需要验证的字符串
     * @param second 超时时间（秒）
     * @return 是否验证成功
     */
    default boolean verifyCode(String code, long second) {
        return true;
    }

}
