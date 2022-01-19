package com.bloom.bloomspringbootdemo.valid;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author taosy
 * Created by on 2022-01-13 上午11:57
 */
@Data
public class Role {

    @NotNull(message = "角色不能为空")
    private String  name;
}