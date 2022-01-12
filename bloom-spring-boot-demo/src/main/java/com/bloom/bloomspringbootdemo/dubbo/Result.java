/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package
        com.bloom.bloomspringbootdemo.dubbo;

/**
 * @author taosy
 * Created by on 2022-01-12 下午5:08
 */

import java.io.Serializable;
import java.util.List;

/**
 * 返回对象，可在controller或者dubbo的rpc中使用
 * @author freeway
 * @param <T> 对应data属性的类型
 */
public interface Result<T> extends Serializable {

    /**
     * 获取错误码
     * @return 错误码
     */
    String getCode();

    /**
     * 获取成功或错误的信息
     * @return 成功或错误的信息
     */
    String getMessage();

    /**
     * 获取数据
     * @return 数据
     */
    T getData();

    /**
     * 获取校验失败的字段
     * @return 校验失败的字段
     */
    List<ViolationItem> getViolationItems();

    /**
     * 获取错误的异常类
     * @return 错误的异常类
     */
    String getErrorClass();

    /**
     * 获取错误的异常堆栈
     * @return 错误的异常堆栈
     */
    String getErrorStack();

    /**
     * 设置错误码
     * @param code 错误码
     * @return Result对象
     */
    Result<T> setCode(String code);

    /**
     * 设置成功或错误的信息
     * @param message 成功或错误的信息
     * @return Result
     */
    Result<T> setMessage(String message);

    /**
     * 设置数据
     * @param data 数据
     * @return Result
     */
    Result<T> setData(T data);

    /**
     * 设置验证失败的字段
     * @param violationItems 验证失败的字段
     * @return Result
     */
    Result<T> setViolationItems(List<ViolationItem> violationItems);

    /**
     * 设置错误的异常类
     * @param errorClass 错误的异常类
     * @return Result
     */
    Result<T> setErrorClass(String errorClass);

    /**
     * 设置错误的异常堆栈
     * @param errorStack 错误的异常堆栈
     * @return Result
     */
    Result<T> setErrorStack(String errorStack);

    /**
     * 添加 ViolationItem
     * @param field ViolationItem 的 field
     * @param message ViolationItem 的 message
     * @return Result
     */
    Result<T> addViolationItem(String field, String message);

    /**
     * 是否成功
     * @return boolean
     */
    boolean isSuccess();

    /**
     * 是否错误
     * @return boolean
     */
    boolean isError();

    /**
     * 是否是业务处理失败，业务异常
     * @return boolean
     */
    boolean isFailure();

    /**
     * Violation Item
     * @author freeway
     */
    interface ViolationItem extends Serializable {

        /**
         * 获取验证失败的字段名
         * @return 验证失败的字段名
         */
        String getField();

        /**
         * 设置验证失败的字段名
         * @param field 验证失败的字段名
         */
        void setField(String field);

        /**
         * 获取验证失败的信息
         * @return 验证失败的信息
         */
        String getMessage();

        /**
         * 设置验证失败的信息
         * @param message 验证失败的信息
         */
        void setMessage(String message);

    }

}