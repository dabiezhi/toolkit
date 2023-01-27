package com.bloom.bloomspringbootdemo;

import io.vavr.control.Try;

/**
 * @author curry
 * Created by on 2023-01-16 3:18 PM
 */
public class TryTest {

    public static void main(String[] args) {
        Try<String> result = Try.ofSupplier(() -> {
            return "1212";
        });
        System.out.println(result.map(u->String.join(u,"1111")).get());
    }

}