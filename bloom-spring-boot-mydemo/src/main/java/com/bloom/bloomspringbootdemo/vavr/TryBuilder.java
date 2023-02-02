package com.bloom.bloomspringbootdemo.vavr;

import io.vavr.control.Try;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author curry
 * Created by on 2023-02-02 10:14 AM
 */
public interface TryBuilder<T> extends Try<T> {

    static <T> Try<T> of(Supplier<? extends T> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        try {
            return Try.success(supplier.get());
        } catch (Throwable t) {
            return Try.failure(t);
        }
    }

//    static <T> Try<T> feign(Supplier<BaseResult<T>> supplier) {
//        Objects.requireNonNull(supplier, "supplier is null");
//        return Try.ofSupplier(supplier).onSuccess(
//                u -> Assert.isTrue(u.isSuccess(), ApplicationCode.SYSTEM_ERROR, u.getDesc())).map(BaseResult::getData);
//    }

}