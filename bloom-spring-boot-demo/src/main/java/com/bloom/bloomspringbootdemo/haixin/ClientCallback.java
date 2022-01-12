/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package
        com.bloom.bloomspringbootdemo.haixin;

/**
 * @author taosy
 * Created by on 2022-01-11 下午5:34
 */
@FunctionalInterface
public interface ClientCallback<T> {

    default void check() {
    }

    BaseResult<T> executeService();
}