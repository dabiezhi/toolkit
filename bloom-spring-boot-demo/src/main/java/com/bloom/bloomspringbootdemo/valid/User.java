/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.valid;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author taosy
 * Created by on 2022-01-13 上午11:57
 */
@Data
public class User {

    @NotNull(message = "用户名不能为空")
    private String     name;
    @NotNull(message = "年龄不能为空")
    private Integer    age;
    @NotEmpty(message = "角色id集合不能为空")
    private List<Role> roleList;
    @EnumValue(message = "枚举类型不正确", enumClass = UserStatusEnum.class, enumMethod = "isValidName")
    private Integer    type;

    public static void main(String[] args) {
        User user = new User();
        user.setType(99);
        Role role = new Role();
        role.setName("超管");
        List<Role> list = new ArrayList<>();
        list.add(role);
        user.setRoleList(list);
        System.out.println(JSON.toJSONString(user));
    }
}