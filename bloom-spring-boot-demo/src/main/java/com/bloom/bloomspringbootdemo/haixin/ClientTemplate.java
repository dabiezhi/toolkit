/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package
        com.bloom.bloomspringbootdemo.haixin;

/**
 * @author taosy
 * Created by on 2022-01-11 下午5:42
 */
public interface ClientTemplate {

    <T> BaseResult<T> execute(ClientCallback<T> action);

}