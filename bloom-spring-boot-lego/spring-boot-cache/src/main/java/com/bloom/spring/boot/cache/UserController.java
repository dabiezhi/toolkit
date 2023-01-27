package com.bloom.spring.boot.cache;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author curry
 * Created by on 2023-01-27 3:51 PM
 */

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/get/{name}")
    public String get(@PathVariable("name") String name) {
        return userService.get(name).toString();
    }
}