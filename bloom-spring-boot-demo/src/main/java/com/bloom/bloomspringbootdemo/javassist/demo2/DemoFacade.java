/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.javassist.demo2;

import javax.validation.Valid;

import com.bloom.bloomspringbootdemo.valid.BaseResult;
import com.bloom.bloomspringbootdemo.valid.User;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author taosy
 * Created by on 2022-01-12 下午4:41
 */
@RequestMapping("/api")
public interface DemoFacade {

    @GetMapping("/demo")
    BaseResult<String> demo(@RequestParam(value = "userId") @NotBlank(message = "字符串不能为空") String userId);

    @PostMapping("/user")
    BaseResult<String> user(@RequestBody @Valid User user);
}