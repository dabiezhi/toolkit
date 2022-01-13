/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.valid;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author taosy
 * Created by on 2022-01-13 下午2:00
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        return invalid(errorList);
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResult<?> handleConstraintViolationException(ConstraintViolationException e) {
        List<BaseResult.ViolationItem> violationItems = ValidationUtils
            .convertToResultViolationItems(e.getConstraintViolations());

        if (violationItems != null) {
            return BaseResult.invalid(violationItems);
        }
        return BaseResult.fail(ErrorConst.INVALID_PARAM);
    }

    private BaseResult<?> invalid(List<FieldError> errors) {
        if (errors != null && errors.size() > 0) {
            return BaseResult.invalid(errors.stream()
                .map(x -> new BaseResult.ViolationItem(x.getField(), x.getDefaultMessage()))
                .collect(Collectors.toList()));
        }
        return BaseResult.fail(ErrorConst.INVALID_PARAM);
    }
}