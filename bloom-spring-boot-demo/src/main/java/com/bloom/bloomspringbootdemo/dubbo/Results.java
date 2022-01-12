/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.dubbo;

/**
 * @author taosy
 * Created by on 2022-01-12 下午5:09
 */

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *  Result工具类，用于返回Result对象
 *  @author freeway
 */
public final class Results {

    /**
     * 成功
     * @return Result<Void>
     */
    public static Result<Void> success() {
        return new DefaultResult<Void>().setCode("").setMessage("");
    }

    /**
     * 成功
     * @param data 并设置data参数
     * @param <T> data的泛型
     * @return Result<T>
     */
    public static <T> Result<T> success(T data) {
        return new DefaultResult<T>().setCode("").setMessage("").setData(data);
    }

    public static <T, R> Result<R> success(T data, Function<T, R> transTFunction) {
        return new DefaultResult<R>().setCode("").setMessage("")
            .setData(transTFunction.apply(data));
    }

}
