/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.autoconfigure.kaptcha;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 验证码配置文件
 *
 * @author taosy
 * @version V1.0
 * @since 2022-01-05 16:14
 */
@ConfigurationProperties(prefix = "bloom.kaptcha")
@Data
public class KaptchaProperties {
    //        properties.setProperty("kaptcha.border","no");
    //
    //        properties.setProperty("kaptcha.textproducer.font.names","Arial, 宋体");
    //        properties.setProperty("kaptcha.textproducer.font.size","60");
    //        properties.setProperty("kaptcha.textproducer.font.color","87,123,36");
    //
    //        properties.setProperty("kaptcha.background.clear.from","247,254,234");
    //        properties.setProperty("kaptcha.background.clear.to","247,254,234");
    //
    //        properties.setProperty("kaptcha.image.width","200");
    //        properties.setProperty("kaptcha.image.height","80");
    //
    //        properties.setProperty("kaptcha.noise.color","114,131,87");
    //        properties.setProperty("kaptcha.noise.impl","com.wedoctor.commerce.werp.biz.configuration.captcha.CaptchaNoise");
    //
    //        properties.setProperty("kaptcha.textproducer.char.length","4");
    //        properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.WaterRipple");

    /**
     * 宽度
     */
    private Integer width = 200;
    /**
     * 高度
     */
    private Integer height = 80;
    /**
     * 内容
     */
    @NestedConfigurationProperty
    private Content content = new Content();

    /**
     * 背景色
     */
    @NestedConfigurationProperty
    private BackgroundColor backgroundColor = new BackgroundColor();
    /**
     * 字体
     */
    @NestedConfigurationProperty
    private Font font = new Font();
    /**
     * 边框
     */
    @NestedConfigurationProperty
    private Border border = new Border();
    /**
     * 干扰线
     */
    @NestedConfigurationProperty
    private Noise noise = new Noise();

    @Data
    static class BackgroundColor {

        /**
         * 开始渐变色
         */
        private String from = "lightGray";
        /**
         * 结束渐变色
         */
        private String to = "white";

    }

    @Data
    static class Border {

        /**
         * 是否开启
         */
        private Boolean enabled = true;
        /**
         * 颜色
         */
        private String color = "black";
        /**
         * 厚度
         */
        private Integer thickness = 1;
    }

    @Data
    static class Content {

        /**
         * 内容源
         */
        private String source = "abcdefghjklmnopqrstuvwxyz23456789";
        /**
         * 内容长度
         */
        private Integer length = 4;
        /**
         * 内容间隔
         */
        private Integer space = 2;
    }

    @Data
    static class Font {

        /**
         * 名称
         */
        private String name = "Arial";
        /**
         * 颜色
         */
        private String color = "black";
        /**
         * 大小
         */
        private Integer size = 40;

    }

    @Data
    static class Noise {

        /**
         * 干扰实现类
         */
        private String impl = "com.bloom.autoconfigure.kaptcha.KaptchaNoise";
        /**
         * 干扰颜色，合法值： r,g,b 或者 white,black,blue.
         */
        private String color = "black";

    }

}
