package com.bloom.springbootcamel;

import org.springframework.stereotype.Component;

/**
 * @author curry
 * Created by on 2022-10-13 下午2:55
 */
@Component
public class MyBean {

    private int counter;

    public String saySomething(String body) {
        return String.format("%s I am invoked %d times", "say", ++counter);
    }
}