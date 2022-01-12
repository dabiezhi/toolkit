/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.dubbo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * result的实现
 * @author freeway
 * @param <T> data字段对应的泛型
 */
public class DefaultResult<T> implements Result<T>, Serializable {

    private static final long   serialVersionUID = -4408341719434417427L;
    private static final String SUCCESS_CODE     = "0";
    private static final String UNKNOWN_ERROR    = "1";
    private static final String ERROR_PREFIX     = "SYS_";

    /**
     * 错误码
     */
    private String              code;

    /**
     * 错误消息
     */
    private String              message;

    /**
     * 响应数据
     */
    //    @Valid
    private T                   data;

    /**
     * 异常类名
     */
    private String              errorClass;

    /**
     * 详细异常信息
     */
    private String              errorStack;

    /**
     * 参数校验错误信息
     */
    private List<ViolationItem> violationItems;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public List<ViolationItem> getViolationItems() {
        return violationItems;
    }

    @Override
    public String getErrorClass() {
        return errorClass;
    }

    @Override
    public String getErrorStack() {
        return errorStack;
    }

    @Override
    public DefaultResult<T> setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public DefaultResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public DefaultResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public DefaultResult<T> setViolationItems(List<ViolationItem> violationItems) {
        this.violationItems = violationItems;
        return this;
    }

    @Override
    public DefaultResult<T> setErrorClass(String errorClass) {
        this.errorClass = errorClass;
        return this;
    }

    @Override
    public DefaultResult<T> setErrorStack(String errorStack) {
        this.errorStack = errorStack;
        return this;
    }

    @Override
    public DefaultResult<T> addViolationItem(String field, String message) {
        if (violationItems == null) {
            violationItems = new ArrayList<>();
        }
        violationItems.add(new DefaultVioationItem(field, message));
        return this;
    }

    @Override
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }

    @Override
    public boolean isError() {
        return UNKNOWN_ERROR.equals(code) || (code != null && code.startsWith(ERROR_PREFIX));
    }

    @Override
    public boolean isFailure() {
        return (!isSuccess()) && (!isError());
    }

    /**
     * ViolationItem的实现
     * @author freeway
     */
    public static class DefaultVioationItem implements ViolationItem, Serializable {

        private static final long serialVersionUID = 2803300694383082237L;

        private String            field;
        private String            message;

        public DefaultVioationItem() {
        }

        public DefaultVioationItem(String field, String message) {
            this.field = field;
            this.message = message;
        }

        @Override
        public String getField() {
            return field;
        }

        @Override
        public void setField(String field) {
            this.field = field;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            DefaultVioationItem that = (DefaultVioationItem) o;

            boolean fieldFlag = field != null ? field.equals(that.field) : that.field == null;
            boolean messageFlag = message != null ? message.equals(that.message)
                : that.message == null;

            return messageFlag && fieldFlag;
        }

        @Override
        public int hashCode() {
            return field != null ? field.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "{" + "field='" + field + '\'' + ", message='" + message + '\'' + '}';
        }

    }

    @Override
    public String toString() {
        return "Result{" + "code='" + code + '\'' + ", message='" + message + '\'' + ", data="
               + data + ", errorClass='" + errorClass + '\'' + ", errorStack='" + errorStack + '\''
               + ", violationItems=" + violationItems + '}';
    }
}
