/**
 * Aistarfish.com Inc.
 * Copyright (c) 2017-2022 All Rights Reserved.
 */
package com.bloom.bloomspringbootdemo.valid;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ElementKind;
import javax.validation.Path;

import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.CollectionUtils;

/**
 * @author taosy
 * Created by on 2022-01-13 下午5:27
 */
public class ValidationUtils {
    public static List<BaseResult.ViolationItem> convertToResultViolationItems(Set<? extends ConstraintViolation<?>> constraintViolations) {
        return convertToResultViolationItems(constraintViolations, null, null);
    }

    public static List<BaseResult.ViolationItem> convertToResultViolationItems(Set<? extends ConstraintViolation<?>> constraintViolations,
                                                                               ParameterNameDiscoverer parameterNameDiscoverer,
                                                                               Method method) {
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return null;
        }
        List<BaseResult.ViolationItem> violationItems = new ArrayList<>(
            constraintViolations.size());
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String fieldName = getFieldName(parameterNameDiscoverer, method, constraintViolation);
            violationItems
                .add(new BaseResult.ViolationItem(fieldName, constraintViolation.getMessage()));
        }
        return violationItems;
    }

    private static String getFieldName(ParameterNameDiscoverer parameterNameDiscoverer,
                                       Method method, ConstraintViolation<?> constraintViolation) {
        String fieldName = "";
        for (Path.Node node : constraintViolation.getPropertyPath()) {
            if (node.getKind() == ElementKind.PARAMETER) {
                fieldName = getParameterName(node, method, parameterNameDiscoverer);
            } else if (node.getKind() == ElementKind.PROPERTY) {
                fieldName = node.getName();
            } else if (node.getKind() == ElementKind.RETURN_VALUE) {
                fieldName = node.getName();
            }
        }
        return fieldName;
    }

    private static String getParameterName(Path.Node node, Method method,
                                           ParameterNameDiscoverer parameterNameDiscoverer) {
        if (parameterNameDiscoverer == null || method == null) {
            return node.getName();
        } else {
            int index = node.as(Path.ParameterNode.class).getParameterIndex();
            if (method.getParameters()[index].isAnnotationPresent(Name.class)) {
                return method.getParameters()[index].getAnnotation(Name.class).value();
            }
            String[] names = parameterNameDiscoverer.getParameterNames(method);
            return names == null ? node.getName() : names[index];
        }
    }
}