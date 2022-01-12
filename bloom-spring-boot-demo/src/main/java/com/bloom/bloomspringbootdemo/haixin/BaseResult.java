/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.haixin;

import lombok.Data;

/**
 * @author taosy
 * Created by on 2022-01-11 下午5:35
 */
@Data
public class BaseResult<T> {

    private boolean isSuccess;

    private String code;

    private String desc;

    private T data;

    public static <T> BaseResult<T> success(T result) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        baseResult.setCode("");
        baseResult.setDesc("");
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

    public boolean isSuccess() {
        return isSuccess;
    }


}