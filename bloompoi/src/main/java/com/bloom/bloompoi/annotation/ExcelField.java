package com.bloom.bloompoi.annotation;

import com.bloom.bloompoi.Constant;
import com.bloom.bloompoi.converter.Converter;
import com.bloom.bloompoi.converter.EmptyConverter;
import com.bloom.bloompoi.enums.RegexType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel的列注解
 *
 * @author bloom
 * @version V1.0
 * @since 2022-01-06 12:10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelField {

    /**
     * Excel列号顺序(必填,从0开始)
     *
     * @return 列号
     */
    int order() default Constant.DEFAULT_ORDER;

    /**
     * Excel列名称(必填,但可以跟excel列名名称不对应)
     *
     * @return 列名称
     */
    String columnName() default Constant.EMPTY_STRING;

    /**
     * Excel字段是否为空(默认可以为空)
     *
     * @return true/false
     */
    boolean nullable() default true;

    /**
     * Excel字段最大长度
     *
     * @return 长度
     */
    int maxLength() default Constant.ZERO;

    /**
     * Excel字段最小长度
     *
     * @return 长度
     */
    int minLength() default Constant.ZERO;

    /**
     * 提供正则表达式进行字段校验
     *
     * @return 正则表达式
     */
    String regExp() default Constant.EMPTY_STRING;

    /**
     * Excel字段日期格式化
     *
     * @return 字段格式化后的字符串
     */
    String datePattern() default Constant.EMPTY_STRING;

    /**
     * 所提供的的一些Excel字段校验注解(单个校验)
     *
     * @return 校验类型
     */
    RegexType regexType() default RegexType.NONE;

    /**
     * 所提供的的一些Excel字段校验注解(多个校验)
     *
     * @return 校验类型
     */
    RegexType[] regexTypes() default {};

    /**
     * Excel字段类型转换器
     *
     * @return 类型转换器
     */
    Class<? extends Converter> convertType() default EmptyConverter.class;

    /**
     * 是否为特殊字段(默认false)
     *
     * @return 特殊注解
     */
    Special special() default @Special();
}
