package com.bloom.bloomspringbootdemo.spring;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author curry
 * Created by on 2023-04-06 5:18 PM
 */
@Component
@Order(2)
public class Toyota implements Car {

    @Override
    public String getName() {
        return "Toyota";
    }
}