/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.dubbo;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author taosy
 * Created by on 2022-01-13 上午11:57
 */
@Data
public class Role {

    @NotNull(message = "角色不能为空")
    private String  name;
}