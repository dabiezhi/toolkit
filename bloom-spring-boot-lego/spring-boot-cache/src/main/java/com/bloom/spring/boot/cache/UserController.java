package com.bloom.spring.boot.cache;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/get")
    public String get() {
        return userService.get("库里").toString();
    }
}