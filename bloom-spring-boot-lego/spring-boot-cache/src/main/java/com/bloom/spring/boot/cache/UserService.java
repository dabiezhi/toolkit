package com.bloom.spring.boot.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author curry
 * Created by on 2023-01-27 3:47 PM
 */
@Service
public class UserService {

    @Cacheable(cacheNames = "user", key = "#name")
    public User get(String name) {
        return User.builder().name(name).build();
    }
}