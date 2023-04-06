package com.bloom.bloomspringbootdemo.spring;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author curry
 * Created by on 2023-04-06 5:19 PM
 */
@Component
@Order(5)
public class Mazda implements Car{

    @Override
    public String getName() {
        return "Toyota";
    }
}