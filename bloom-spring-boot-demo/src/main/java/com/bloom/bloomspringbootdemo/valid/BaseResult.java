/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.valid;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author taosy
 * Created by on 2022-01-11 下午5:35
 */
@Data
public class BaseResult<T> {

    private boolean             isSuccess;

    private String              code;

    private String              desc;

    private T                   data;

    private List<ViolationItem> violationItems;

    public static <T> BaseResult<T> success(T result) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        baseResult.setCode(ErrorConst.SUCCESS.getCode());
        baseResult.setDesc(ErrorConst.SUCCESS.getCode());
        baseResult.setData(result);
        return baseResult;
    }

    public static <T> BaseResult<T> fail(T result, String errorCode, String errorMsg) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setData(result);
        baseResult.setCode(errorCode);
        baseResult.setDesc(errorMsg);
        baseResult.setSuccess(false);
        return baseResult;
    }

    public static <T> BaseResult<T> fail(String errorCode, String errorMsg) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(errorCode);
        baseResult.setDesc(errorMsg);
        baseResult.setSuccess(false);
        return baseResult;
    }

    public static <T> BaseResult<T> fail(ErrorConst errorConst) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(errorConst.getCode());
        baseResult.setDesc(errorConst.getDesc());
        baseResult.setSuccess(false);
        return baseResult;
    }

    public static <T> BaseResult<T> invalid(List<BaseResult.ViolationItem> violationItems) {
        BaseResult<T> baseResult = fail(ErrorConst.INVALID_PARAM);
        baseResult.setViolationItems(violationItems);
        return baseResult;
    }

    @Data
    static class ViolationItem implements Serializable {
        private String field;
        private String message;

        public ViolationItem(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}