/*
 * Copyright (c) 2001-2022 GuaHao.com Corporation Limited. All rights reserved.
 * This software is the confidential and proprietary information of GuaHao Company.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.bloom.autoconfigure.kaptcha;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 *
 * @author taosy
 * @version V1.0
 * @since 2022-01-05 16:12
 */
@Configuration
@ConditionalOnClass(DefaultKaptcha.class)
@EnableConfigurationProperties(KaptchaProperties.class)
public class KaptchaAutoConfiguration {
    private final KaptchaProperties properties;

    public KaptchaAutoConfiguration(KaptchaProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultKaptcha defaultKaptcha() {
        Properties prop = new Properties();
        //宽度 高度
        prop.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, String.valueOf(properties.getWidth()));
        prop.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, String.valueOf(properties.getHeight()));
        //内容
        KaptchaProperties.Content content = properties.getContent();
        prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, content.getSource());
        prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, String.valueOf(content.getLength()));
        prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, String.valueOf(content.getSpace()));
        //北京
        KaptchaProperties.BackgroundColor backgroundColor = properties.getBackgroundColor();
        prop.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM, backgroundColor.getFrom());
        prop.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO, backgroundColor.getTo());
        //边框
        KaptchaProperties.Border border = properties.getBorder();
        prop.setProperty(Constants.KAPTCHA_BORDER, border.getEnabled() ? "yes" : "no");
        prop.setProperty(Constants.KAPTCHA_BORDER_COLOR, border.getColor());
        prop.setProperty(Constants.KAPTCHA_BORDER_THICKNESS, String.valueOf(border.getThickness()));
        //字体
        KaptchaProperties.Font font = properties.getFont();
        prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, font.getName());
        prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, String.valueOf(font.getSize()));
        prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, font.getColor());
        //干扰线
        KaptchaProperties.Noise noise = properties.getNoise();
        prop.setProperty(Constants.KAPTCHA_NOISE_IMPL, noise.getImpl());
        prop.setProperty(Constants.KAPTCHA_NOISE_COLOR, noise.getColor());

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(prop));
        return defaultKaptcha;
    }

    @Bean
    @ConditionalOnMissingBean
    public KaptchaRender render() {
        return new KaptchaRender();
    }

}
