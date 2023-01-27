package com.bloom.spring.boot.cache;

import lombok.Builder;

/**
 * @author curry
 * Created by on 2023-01-27 3:44 PM
 */
@Builder
public class User {

    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}